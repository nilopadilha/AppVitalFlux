package br.com.solivos.AppVitalFlux.domain.repository.clinico;

import br.com.solivos.AppVitalFlux.domain.model.clinico.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, UUID> {
    Optional<Profissional> findByRegistroProfissional(String registroProfissional);
}
