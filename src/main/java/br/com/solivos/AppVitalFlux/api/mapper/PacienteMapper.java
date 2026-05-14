package br.com.solivos.AppVitalFlux.api.mapper;

import br.com.solivos.AppVitalFlux.api.dto.input.PacienteInputDTO;
import br.com.solivos.AppVitalFlux.api.dto.output.PacienteDTO;
import br.com.solivos.AppVitalFlux.domain.model.clinico.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteDTO toDTO(Paciente paciente);

    List<PacienteDTO> toCollectionDTO(List<Paciente> pacientes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Paciente toEntity(PacienteInputDTO pacienteInputDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void copyToEntity(PacienteInputDTO pacienteInputDTO, @MappingTarget Paciente paciente);
}
