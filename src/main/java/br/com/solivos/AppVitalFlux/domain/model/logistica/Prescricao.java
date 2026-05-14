package br.com.solivos.AppVitalFlux.domain.model.logistica;

import br.com.solivos.AppVitalFlux.domain.enums.StatusPrescricao;
import br.com.solivos.AppVitalFlux.domain.model.clinico.Atendimento;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "prescricao", schema = "logistica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendimento_id", nullable = false)
    private Atendimento atendimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPrescricao status;

    @Builder.Default
    @Column(name = "data_emissao", updatable = false)
    private LocalDateTime dataEmissao = LocalDateTime.now();

    @Builder.Default
    @OneToMany(mappedBy = "prescricao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPrescricao> itens = new ArrayList<>();

    public void adicionarItem(ItemPrescricao item) {
        itens.add(item);
        item.setPrescricao(this);
    }
}
