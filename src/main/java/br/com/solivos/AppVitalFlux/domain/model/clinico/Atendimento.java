package br.com.solivos.AppVitalFlux.domain.model.clinico;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "atendimento", schema = "clinico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_id", nullable = false)
    private UnidadeSaude unidadeSaude;

    @Column(name = "queixa_principal", columnDefinition = "TEXT")
    private String queixaPrincipal;

    @Column(name = "diagnostico_cid10", length = 10)
    private String diagnosticoCid10;

    @Builder.Default
    @Column(name = "data_atendimento", updatable = false)
    private LocalDateTime dataAtendimento = LocalDateTime.now();
}
