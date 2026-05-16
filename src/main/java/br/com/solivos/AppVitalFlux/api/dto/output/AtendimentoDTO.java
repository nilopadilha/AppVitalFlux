package br.com.solivos.AppVitalFlux.api.dto.output;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AtendimentoDTO {

    private UUID id;
    private PacienteDTO paciente;
    private UUID profissionalId;
    private String profissionalNome;
    private UUID unidadeSaudeId;
    private String unidadeSaudeNome;
    private String queixaPrincipal;
    private String diagnosticoCid10;
    private LocalDateTime dataAtendimento;
}
