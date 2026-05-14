package br.com.solivos.AppVitalFlux.domain.model.clinico;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "profissional", schema = "clinico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(name = "registro_profissional", unique = true, nullable = false, length = 50)
    private String registroProfissional;

    @Column(length = 100)
    private String especialidade;
}
