package br.com.solivos.AppVitalFlux.domain.service.clinico;

import br.com.solivos.AppVitalFlux.domain.exception.EntidadeNaoEncontradaException;
import br.com.solivos.AppVitalFlux.domain.model.clinico.UnidadeSaude;
import br.com.solivos.AppVitalFlux.domain.repository.clinico.UnidadeSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UnidadeSaudeService {

    private final UnidadeSaudeRepository unidadeSaudeRepository;

    public UnidadeSaude buscarOuFalhar(UUID id) {
        return unidadeSaudeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Unidade de saúde não encontrada"));
    }
}
