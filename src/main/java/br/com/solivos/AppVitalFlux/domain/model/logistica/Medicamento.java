package br.com.solivos.AppVitalFlux.domain.model.logistica;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "medicamento", schema = "logistica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "codigo_catmat", unique = true, length = 20)
    private String codigoCatmat;

    @Column(name = "nome_generico", nullable = false, length = 255)
    private String nomeGenerico;

    @Column(length = 100)
    private String concentracao;

    @Column(name = "forma_farmaceutica", length = 100)
    private String formaFarmaceutica;

    @Builder.Default
    @Column(name = "e_alto_custo")
    private Boolean eAltoCusto = false;
}
