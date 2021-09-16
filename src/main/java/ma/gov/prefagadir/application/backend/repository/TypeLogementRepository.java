package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.TypeLogement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeLogementRepository extends JpaRepository<TypeLogement, Long> {
}
