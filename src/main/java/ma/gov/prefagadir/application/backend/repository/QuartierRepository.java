package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.Quartier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartierRepository extends JpaRepository<Quartier, Long> {
}
