package br.com.solivos.AppVitalFlux.api.mapper;

import br.com.solivos.AppVitalFlux.api.dto.input.AtendimentoInputDTO;
import br.com.solivos.AppVitalFlux.api.dto.output.AtendimentoDTO;
import br.com.solivos.AppVitalFlux.domain.model.clinico.Atendimento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AtendimentoMapper {

    @Mapping(source = "profissional.id", target = "profissionalId")
    @Mapping(source = "profissional.nome", target = "profissionalNome")
    @Mapping(source = "unidadeSaude.id", target = "unidadeSaudeId")
    @Mapping(source = "unidadeSaude.nome", target = "unidadeSaudeNome")
    AtendimentoDTO toDTO(Atendimento atendimento);

    List<AtendimentoDTO> toCollectionDTO(List<Atendimento> atendimentos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "profissional", ignore = true)
    @Mapping(target = "unidadeSaude", ignore = true)
    @Mapping(target = "dataAtendimento", ignore = true)
    Atendimento toEntity(AtendimentoInputDTO atendimentoInputDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "profissional", ignore = true)
    @Mapping(target = "unidadeSaude", ignore = true)
    @Mapping(target = "dataAtendimento", ignore = true)
    void copyToEntity(AtendimentoInputDTO atendimentoInputDTO, @MappingTarget Atendimento atendimento);
}
