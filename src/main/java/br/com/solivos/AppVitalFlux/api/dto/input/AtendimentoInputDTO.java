package br.com.solivos.AppVitalFlux.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class AtendimentoInputDTO {

    @NotNull
    private UUID pacienteId;

    @NotNull
    private UUID profissionalId;

    @NotNull
    private UUID unidadeSaudeId;

    private String queixaPrincipal;

    private String diagnosticoCid10;
}
