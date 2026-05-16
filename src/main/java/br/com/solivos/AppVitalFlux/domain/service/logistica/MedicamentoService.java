package br.com.solivos.AppVitalFlux.domain.service.logistica;

import br.com.solivos.AppVitalFlux.domain.exception.EntidadeNaoEncontradaException;
import br.com.solivos.AppVitalFlux.domain.model.logistica.Medicamento;
import br.com.solivos.AppVitalFlux.domain.repository.logistica.MedicamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public Medicamento buscarOuFalhar(UUID id) {
        return medicamentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Medicamento não encontrado"));
    }
}
