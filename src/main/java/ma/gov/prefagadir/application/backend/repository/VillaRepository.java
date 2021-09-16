package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.Villa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillaRepository extends JpaRepository<Villa, Long> {
}
