package br.com.solivos.AppVitalFlux.api.dto.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PrescricaoInputDTO {

    @NotNull
    private UUID atendimentoId;

    @NotEmpty
    private List<ItemPrescricaoInputDTO> itens;
}
