package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.Immeuble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmebleRepository extends JpaRepository<Immeuble, Long> {
}
