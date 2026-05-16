package br.com.solivos.AppVitalFlux.domain.service.logistica;

import br.com.solivos.AppVitalFlux.domain.enums.StatusPrescricao;
import br.com.solivos.AppVitalFlux.domain.exception.EntidadeNaoEncontradaException;
import br.com.solivos.AppVitalFlux.domain.model.logistica.Prescricao;
import br.com.solivos.AppVitalFlux.domain.repository.logistica.PrescricaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescricaoService {

    private final PrescricaoRepository prescricaoRepository;

    @Transactional
    public Prescricao emitir(Prescricao prescricao) {
        prescricao.setStatus(StatusPrescricao.ATIVA);
        return prescricaoRepository.save(prescricao);
    }

    @Transactional
    public void excluir(UUID id) {
        Prescricao prescricao = buscarOuFalhar(id);
        prescricaoRepository.delete(prescricao);
    }

    public Prescricao buscarOuFalhar(UUID id) {
        return prescricaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Prescrição não encontrada"));
    }
}
