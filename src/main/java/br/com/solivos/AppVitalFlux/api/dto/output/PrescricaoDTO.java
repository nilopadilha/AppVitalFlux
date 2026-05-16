package br.com.solivos.AppVitalFlux.api.dto.output;

import br.com.solivos.AppVitalFlux.domain.enums.StatusPrescricao;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PrescricaoDTO {
    private UUID id;
    private UUID atendimentoId;
    private StatusPrescricao status;
    private LocalDateTime dataEmissao;
    private List<ItemPrescricaoDTO> itens;
}
