-- Habilitar extensão para geração de UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Schema para organização
CREATE SCHEMA IF NOT EXISTS clinico;
CREATE SCHEMA IF NOT EXISTS logistica;

-- Tabela de Unidades de Saúde (PSF, Hospitais)
CREATE TABLE clinico.unidade_saude (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(150) NOT NULL,
    cnes VARCHAR(20) UNIQUE NOT NULL,
    tipo VARCHAR(50) NOT NULL, -- 'PSF', 'HOSPITAL', 'URGENCIA'
    cidade VARCHAR(100) NOT NULL
);

-- Tabela de Pacientes
CREATE TABLE clinico.paciente (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    cns VARCHAR(15) UNIQUE, -- Cartão Nacional de Saúde
    nome_completo VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    endereco TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Profissionais (Médicos, Enfermeiros)
CREATE TABLE clinico.profissional (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255) NOT NULL,
    registro_profissional VARCHAR(50) UNIQUE NOT NULL, -- CRM/COREN
    especialidade VARCHAR(100)
);

-- Tabela de Medicamentos (Mestre - Integrada à RENAME/UNICAT)
CREATE TABLE logistica.medicamento (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    codigo_catmat VARCHAR(20) UNIQUE,
    nome_generico VARCHAR(255) NOT NULL,
    concentracao VARCHAR(100),
    forma_farmaceutica VARCHAR(100),
    e_alto_custo BOOLEAN DEFAULT FALSE
);

-- Prontuário/Consulta (O vínculo inicial)
CREATE TABLE clinico.atendimento (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    paciente_id UUID REFERENCES clinico.paciente(id),
    profissional_id UUID REFERENCES clinico.profissional(id),
    unidade_id UUID REFERENCES clinico.unidade_saude(id),
    queixa_principal TEXT,
    diagnostico_cid10 VARCHAR(10),
    data_atendimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Prescricao vinculada ao atendimento
CREATE TABLE logistica.prescricao (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    atendimento_id UUID REFERENCES clinico.atendimento(id),
    status VARCHAR(20) NOT NULL, -- 'ATIVA', 'DISPENSADA', 'AGUARDANDO_UNICAT'
    data_emissao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Itens da Prescricao (Onde a UNICAT lê a demanda)
CREATE TABLE logistica.item_prescricao (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    prescricao_id UUID REFERENCES logistica.prescricao(id),
    medicamento_id UUID REFERENCES logistica.medicamento(id),
    quantidade INTEGER NOT NULL,
    posologia TEXT
);
