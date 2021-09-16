package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.AAL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AALRepository extends JpaRepository<AAL, Long> {
}
