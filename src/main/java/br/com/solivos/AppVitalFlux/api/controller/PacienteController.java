package br.com.solivos.AppVitalFlux.api.controller;

import br.com.solivos.AppVitalFlux.api.dto.input.PacienteInputDTO;
import br.com.solivos.AppVitalFlux.api.dto.output.PacienteDTO;
import br.com.solivos.AppVitalFlux.api.mapper.PacienteMapper;
import br.com.solivos.AppVitalFlux.domain.model.clinico.Paciente;
import br.com.solivos.AppVitalFlux.domain.repository.clinico.PacienteRepository;
import br.com.solivos.AppVitalFlux.domain.service.clinico.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteRepository pacienteRepository;
    private final PacienteService pacienteService;
    private final PacienteMapper pacienteMapper;

    @GetMapping
    public List<PacienteDTO> listar() {
        List<Paciente> todosPacientes = pacienteRepository.findAll();
        return pacienteMapper.toCollectionDTO(todosPacientes);
    }

    @GetMapping("/{pacienteId}")
    public PacienteDTO buscar(@PathVariable UUID pacienteId) {
        Paciente paciente = pacienteService.buscarOuFalhar(pacienteId);
        return pacienteMapper.toDTO(paciente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteDTO adicionar(@RequestBody @Valid PacienteInputDTO pacienteInputDTO) {
        Paciente novoPaciente = pacienteMapper.toEntity(pacienteInputDTO);
        novoPaciente = pacienteService.salvar(novoPaciente);
        return pacienteMapper.toDTO(novoPaciente);
    }

    @PutMapping("/{pacienteId}")
    public PacienteDTO atualizar(@PathVariable UUID pacienteId, @RequestBody @Valid PacienteInputDTO pacienteInputDTO) {
        Paciente pacienteAtual = pacienteService.buscarOuFalhar(pacienteId);
        pacienteMapper.copyToEntity(pacienteInputDTO, pacienteAtual);
        pacienteAtual = pacienteService.salvar(pacienteAtual);
        return pacienteMapper.toDTO(pacienteAtual);
    }

    @DeleteMapping("/{pacienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID pacienteId) {
        pacienteService.excluir(pacienteId);
    }
}
