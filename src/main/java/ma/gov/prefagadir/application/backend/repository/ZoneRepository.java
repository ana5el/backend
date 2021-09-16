package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
}
