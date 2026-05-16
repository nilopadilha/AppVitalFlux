package br.com.solivos.AppVitalFlux.domain.service.clinico;

import br.com.solivos.AppVitalFlux.domain.exception.EntidadeNaoEncontradaException;
import br.com.solivos.AppVitalFlux.domain.model.clinico.Atendimento;
import br.com.solivos.AppVitalFlux.domain.repository.clinico.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    @Transactional
    public Atendimento salvar(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    @Transactional
    public void excluir(UUID id) {
        Atendimento atendimento = buscarOuFalhar(id);
        atendimentoRepository.delete(atendimento);
    }

    public Atendimento buscarOuFalhar(UUID id) {
        return atendimentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Atendimento não encontrado"));
    }
}
