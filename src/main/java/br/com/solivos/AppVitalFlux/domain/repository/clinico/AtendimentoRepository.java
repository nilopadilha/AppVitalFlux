package br.com.solivos.AppVitalFlux.domain.repository.clinico;

import br.com.solivos.AppVitalFlux.domain.model.clinico.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, UUID> {
}
