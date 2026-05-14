package br.com.solivos.AppVitalFlux.api.dto.output;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PacienteDTO {
    private UUID id;
    private String cpf;
    private String cns;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String endereco;
}
