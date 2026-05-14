package br.com.solivos.AppVitalFlux.domain.repository.logistica;

import br.com.solivos.AppVitalFlux.domain.model.logistica.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, UUID> {
    Optional<Medicamento> findByCodigoCatmat(String codigoCatmat);
}
