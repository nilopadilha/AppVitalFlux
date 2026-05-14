package br.com.solivos.AppVitalFlux.domain.service.clinico;

import br.com.solivos.AppVitalFlux.domain.exception.EntidadeNaoEncontradaException;
import br.com.solivos.AppVitalFlux.domain.exception.NegocioException;
import br.com.solivos.AppVitalFlux.domain.model.clinico.Paciente;
import br.com.solivos.AppVitalFlux.domain.repository.clinico.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Transactional
    public Paciente salvar(Paciente paciente) {
        // Regra de negócio: Verificar se CPF já existe
        pacienteRepository.findByCpf(paciente.getCpf())
                .ifPresent(p -> {
                    if (!p.getId().equals(paciente.getId())) {
                        throw new NegocioException("Já existe um paciente cadastrado com este CPF");
                    }
                });

        return pacienteRepository.save(paciente);
    }

    public Paciente buscarOuFalhar(UUID id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente não encontrado"));
    }

    @Transactional
    public void excluir(UUID id) {
        Paciente paciente = buscarOuFalhar(id);
        pacienteRepository.delete(paciente);
    }
}
