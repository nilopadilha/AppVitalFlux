package br.com.solivos.AppVitalFlux.domain.repository.logistica;

import br.com.solivos.AppVitalFlux.domain.model.logistica.Prescricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PrescricaoRepository extends JpaRepository<Prescricao, UUID> {
}
