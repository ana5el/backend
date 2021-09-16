package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogementRepository extends JpaRepository<Logement, Long> {
}
