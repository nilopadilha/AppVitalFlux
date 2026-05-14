package br.com.solivos.AppVitalFlux.domain.model.clinico;

import br.com.solivos.AppVitalFlux.domain.enums.TipoUnidadeSaude;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "unidade_saude", schema = "clinico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadeSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(unique = true, nullable = false, length = 20)
    private String cnes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoUnidadeSaude tipo;

    @Column(nullable = false, length = 100)
    private String cidade;
}
