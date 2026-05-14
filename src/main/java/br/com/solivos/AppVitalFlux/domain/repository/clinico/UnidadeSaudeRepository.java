package br.com.solivos.AppVitalFlux.domain.repository.clinico;

import br.com.solivos.AppVitalFlux.domain.model.clinico.UnidadeSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, UUID> {
    Optional<UnidadeSaude> findByCnes(String cnes);
}
