package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.AgentAutorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentAutoriteRepository extends JpaRepository<AgentAutorite, Long> {
}
