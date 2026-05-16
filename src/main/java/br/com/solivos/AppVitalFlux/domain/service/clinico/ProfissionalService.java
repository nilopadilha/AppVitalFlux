package br.com.solivos.AppVitalFlux.domain.service.clinico;

import br.com.solivos.AppVitalFlux.domain.exception.EntidadeNaoEncontradaException;
import br.com.solivos.AppVitalFlux.domain.model.clinico.Profissional;
import br.com.solivos.AppVitalFlux.domain.repository.clinico.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    public Profissional buscarOuFalhar(UUID id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Profissional não encontrado"));
    }
}
