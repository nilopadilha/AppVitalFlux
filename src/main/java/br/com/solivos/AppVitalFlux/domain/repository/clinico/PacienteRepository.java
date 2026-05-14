package br.com.solivos.AppVitalFlux.domain.repository.clinico;

import br.com.solivos.AppVitalFlux.domain.model.clinico.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    Optional<Paciente> findByCpf(String cpf);
    Optional<Paciente> findByCns(String cns);
}
