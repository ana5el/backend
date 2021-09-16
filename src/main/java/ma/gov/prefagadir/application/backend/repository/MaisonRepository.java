package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.Maison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaisonRepository extends JpaRepository<Maison, Long> {
}
