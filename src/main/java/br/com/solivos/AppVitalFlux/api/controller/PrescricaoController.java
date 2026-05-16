package br.com.solivos.AppVitalFlux.api.controller;

import br.com.solivos.AppVitalFlux.api.dto.input.PrescricaoInputDTO;
import br.com.solivos.AppVitalFlux.api.dto.input.ItemPrescricaoInputDTO;
import br.com.solivos.AppVitalFlux.api.dto.output.PrescricaoDTO;
import br.com.solivos.AppVitalFlux.api.mapper.PrescricaoMapper;
import br.com.solivos.AppVitalFlux.domain.model.logistica.Prescricao;
import br.com.solivos.AppVitalFlux.domain.model.logistica.ItemPrescricao;
import br.com.solivos.AppVitalFlux.domain.repository.logistica.PrescricaoRepository;
import br.com.solivos.AppVitalFlux.domain.service.clinico.AtendimentoService;
import br.com.solivos.AppVitalFlux.domain.service.logistica.MedicamentoService;
import br.com.solivos.AppVitalFlux.domain.service.logistica.PrescricaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prescricoes")
@RequiredArgsConstructor
public class PrescricaoController {

    private final PrescricaoRepository prescricaoRepository;
    private final PrescricaoService prescricaoService;
    private final PrescricaoMapper prescricaoMapper;
    private final AtendimentoService atendimentoService;
    private final MedicamentoService medicamentoService;

    @GetMapping
    public List<PrescricaoDTO> listar() {
        return prescricaoMapper.toCollectionDTO(prescricaoRepository.findAll());
    }

    @GetMapping("/{prescricaoId}")
    public PrescricaoDTO buscar(@PathVariable UUID prescricaoId) {
        return prescricaoMapper.toDTO(prescricaoService.buscarOuFalhar(prescricaoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrescricaoDTO emitir(@RequestBody @Valid PrescricaoInputDTO prescricaoInputDTO) {
        Prescricao novaPrescricao = prescricaoMapper.toEntity(prescricaoInputDTO);
        
        novaPrescricao.setAtendimento(atendimentoService.buscarOuFalhar(prescricaoInputDTO.getAtendimentoId()));
        
        for (ItemPrescricaoInputDTO itemInput : prescricaoInputDTO.getItens()) {
            ItemPrescricao item = prescricaoMapper.toItemEntity(itemInput);
            item.setMedicamento(medicamentoService.buscarOuFalhar(itemInput.getMedicamentoId()));
            novaPrescricao.adicionarItem(item);
        }

        novaPrescricao = prescricaoService.emitir(novaPrescricao);
        return prescricaoMapper.toDTO(novaPrescricao);
    }

    @DeleteMapping("/{prescricaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID prescricaoId) {
        prescricaoService.excluir(prescricaoId);
    }
}
