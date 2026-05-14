# AppVitalFlux - Sistema de PEP Longitudinal e Logística UNICAT

O **AppVitalFlux** é um sistema de Prontuário Eletrônico do Paciente (PEP) longitudinal integrado à logística da UNICAT (Rio Grande do Norte). O objetivo é gerenciar a jornada do paciente desde a Unidade Básica (PSF) até o monitoramento de demanda por medicamentos de alto custo.

## 🚀 Stack Tecnológica

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.4.x
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** PostgreSQL 16+
- **Migrações:** Flyway
- **Mapeamento:** MapStruct 1.6
- **Utilitários:** Lombok
- **Containerização:** Docker / Docker Compose
- **Documentação de Erros:** RFC 7807 (Problem Details)

## 🏗️ Arquitetura e Design

O projeto segue os princípios de **Clean Code** e **SOLID**, com as seguintes decisões arquiteturais:

- **Identificadores Únicos:** Uso de `UUID` (Universally Unique Identifier) como Chave Primária (PK) em todas as tabelas para suportar sincronização distribuída e segurança de IDs não sequenciais.
- **Isolamento de Contexto (Schemas):**
    - `clinico`: Gerencia dados de pacientes, unidades de saúde, profissionais e atendimentos.
    - `logistica`: Gerencia medicamentos (CATMAT/RENAME), prescrições e itens de demanda para a UNICAT.
- **Camadas:**
    - `Controller`: Exposição de endpoints REST utilizando DTOs para evitar exposição de entidades.
    - `Service`: Centralização das regras de negócio e controle transacional.
    - `Repository`: Abstração de acesso aos dados via Spring Data JPA.
    - `Mapper`: Conversão performática entre Entidades e DTOs via MapStruct.
- **Tratamento de Exceções:** Global Exception Handler que retorna erros padronizados, evitando vazamento de stacktrace e melhorando a experiência do desenvolvedor front-end.

## 📊 Modelo de Dados (MVP)

### Schema Clínico
- **Paciente:** Cadastro nacional de saúde (CNS), CPF e dados pessoais.
- **UnidadeSaude:** Postos de saúde (PSF), hospitais e unidades de urgência (CNES).
- **Profissional:** Registro profissional (CRM/COREN) e especialidades.
- **Atendimento:** O núcleo do prontuário, vinculando paciente, médico e unidade.

### Schema Logística
- **Medicamento:** Catálogo integrado ao CATMAT/RENAME.
- **Prescricao:** Status da demanda (ATIVA, DISPENSADA, AGUARDANDO_UNICAT).
- **ItemPrescricao:** Detalhamento de quantidade e posologia.

## 🛠️ Como Executar

### Pré-requisitos
- JDK 21
- Maven 3.9+
- Docker e Docker Compose

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/nilopadilha/AppVitalFlux.git
   ```
2. Suba o banco de dados via Docker:
   ```bash
   docker-compose up -d
   ```
3. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

A aplicação iniciará na porta `8080`. O Flyway executará automaticamente as migrações em `src/main/resources/db/migration`.

## 🛣️ Endpoints Principais (Exemplo)

- `GET /pacientes`: Listar todos os pacientes.
- `GET /pacientes/{id}`: Buscar paciente por ID.
- `POST /pacientes`: Cadastrar novo paciente.
- `PUT /pacientes/{id}`: Atualizar dados do paciente.
- `DELETE /pacientes/{id}`: Remover paciente.

---
**Desenvolvido por Nilo Padilha**
