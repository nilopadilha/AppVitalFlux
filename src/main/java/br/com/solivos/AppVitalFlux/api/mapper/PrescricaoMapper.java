package br.com.solivos.AppVitalFlux.api.mapper;

import br.com.solivos.AppVitalFlux.api.dto.input.PrescricaoInputDTO;
import br.com.solivos.AppVitalFlux.api.dto.input.ItemPrescricaoInputDTO;
import br.com.solivos.AppVitalFlux.api.dto.output.PrescricaoDTO;
import br.com.solivos.AppVitalFlux.api.dto.output.ItemPrescricaoDTO;
import br.com.solivos.AppVitalFlux.domain.model.logistica.Prescricao;
import br.com.solivos.AppVitalFlux.domain.model.logistica.ItemPrescricao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PrescricaoMapper {

    @Mapping(source = "atendimento.id", target = "atendimentoId")
    PrescricaoDTO toDTO(Prescricao prescricao);

    List<PrescricaoDTO> toCollectionDTO(List<Prescricao> prescricoes);

    @Mapping(source = "medicamento.id", target = "medicamentoId")
    @Mapping(source = "medicamento.nomeGenerico", target = "medicamentoNome")
    ItemPrescricaoDTO toItemDTO(ItemPrescricao item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "atendimento", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "dataEmissao", ignore = true)
    Prescricao toEntity(PrescricaoInputDTO prescricaoInputDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prescricao", ignore = true)
    @Mapping(target = "medicamento", ignore = true)
    ItemPrescricao toItemEntity(ItemPrescricaoInputDTO itemInputDTO);
}
