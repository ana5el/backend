package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
