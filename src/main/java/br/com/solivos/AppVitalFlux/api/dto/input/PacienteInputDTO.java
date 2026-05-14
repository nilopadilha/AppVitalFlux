package br.com.solivos.AppVitalFlux.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PacienteInputDTO {

    @NotBlank
    @Size(min = 11, max = 14)
    private String cpf;

    @Size(max = 15)
    private String cns;

    @NotBlank
    @Size(max = 255)
    private String nomeCompleto;

    @NotNull
    private LocalDate dataNascimento;

    private String endereco;
}
