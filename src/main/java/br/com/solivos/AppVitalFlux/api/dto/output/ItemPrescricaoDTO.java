package br.com.solivos.AppVitalFlux.api.dto.output;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class ItemPrescricaoDTO {
    private UUID id;
    private UUID medicamentoId;
    private String medicamentoNome;
    private Integer quantidade;
    private String posologia;
}
