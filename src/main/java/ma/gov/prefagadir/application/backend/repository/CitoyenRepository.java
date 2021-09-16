package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitoyenRepository extends JpaRepository<Citoyen, String> {
}
