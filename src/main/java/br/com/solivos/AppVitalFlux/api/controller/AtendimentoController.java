package br.com.solivos.AppVitalFlux.api.controller;

import br.com.solivos.AppVitalFlux.api.dto.input.AtendimentoInputDTO;
import br.com.solivos.AppVitalFlux.api.dto.output.AtendimentoDTO;
import br.com.solivos.AppVitalFlux.api.mapper.AtendimentoMapper;
import br.com.solivos.AppVitalFlux.domain.model.clinico.Atendimento;
import br.com.solivos.AppVitalFlux.domain.repository.clinico.AtendimentoRepository;
import br.com.solivos.AppVitalFlux.domain.service.clinico.AtendimentoService;
import br.com.solivos.AppVitalFlux.domain.service.clinico.PacienteService;
import br.com.solivos.AppVitalFlux.domain.service.clinico.ProfissionalService;
import br.com.solivos.AppVitalFlux.domain.service.clinico.UnidadeSaudeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {

    private final AtendimentoRepository atendimentoRepository;
    private final AtendimentoService atendimentoService;
    private final AtendimentoMapper atendimentoMapper;
    private final PacienteService pacienteService;
    private final ProfissionalService profissionalService;
    private final UnidadeSaudeService unidadeSaudeService;

    @GetMapping
    public List<AtendimentoDTO> listar() {
        return atendimentoMapper.toCollectionDTO(atendimentoRepository.findAll());
    }

    @GetMapping("/{atendimentoId}")
    public AtendimentoDTO buscar(@PathVariable UUID atendimentoId) {
        return atendimentoMapper.toDTO(atendimentoService.buscarOuFalhar(atendimentoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtendimentoDTO adicionar(@RequestBody @Valid AtendimentoInputDTO atendimentoInputDTO) {
        Atendimento novoAtendimento = atendimentoMapper.toEntity(atendimentoInputDTO);
        
        novoAtendimento.setPaciente(pacienteService.buscarOuFalhar(atendimentoInputDTO.getPacienteId()));
        novoAtendimento.setProfissional(profissionalService.buscarOuFalhar(atendimentoInputDTO.getProfissionalId()));
        novoAtendimento.setUnidadeSaude(unidadeSaudeService.buscarOuFalhar(atendimentoInputDTO.getUnidadeSaudeId()));
        
        novoAtendimento = atendimentoService.salvar(novoAtendimento);
        return atendimentoMapper.toDTO(novoAtendimento);
    }

    @PutMapping("/{atendimentoId}")
    public AtendimentoDTO atualizar(@PathVariable UUID atendimentoId, @RequestBody @Valid AtendimentoInputDTO atendimentoInputDTO) {
        Atendimento atendimentoAtual = atendimentoService.buscarOuFalhar(atendimentoId);
        atendimentoMapper.copyToEntity(atendimentoInputDTO, atendimentoAtual);
        
        atendimentoAtual.setPaciente(pacienteService.buscarOuFalhar(atendimentoInputDTO.getPacienteId()));
        atendimentoAtual.setProfissional(profissionalService.buscarOuFalhar(atendimentoInputDTO.getProfissionalId()));
        atendimentoAtual.setUnidadeSaude(unidadeSaudeService.buscarOuFalhar(atendimentoInputDTO.getUnidadeSaudeId()));
        
        atendimentoAtual = atendimentoService.salvar(atendimentoAtual);
        return atendimentoMapper.toDTO(atendimentoAtual);
    }

    @DeleteMapping("/{atendimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID atendimentoId) {
        atendimentoService.excluir(atendimentoId);
    }
}
