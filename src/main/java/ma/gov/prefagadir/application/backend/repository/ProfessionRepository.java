package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}
