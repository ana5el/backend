package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.ZonePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonePointRepository extends JpaRepository<ZonePoint, Long> {
}
