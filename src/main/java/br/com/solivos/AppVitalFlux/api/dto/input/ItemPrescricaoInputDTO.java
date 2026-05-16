package br.com.solivos.AppVitalFlux.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class ItemPrescricaoInputDTO {

    @NotNull
    private UUID medicamentoId;

    @NotNull
    private Integer quantidade;

    private String posologia;
}
