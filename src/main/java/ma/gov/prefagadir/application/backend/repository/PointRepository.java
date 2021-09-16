package ma.gov.prefagadir.application.backend.repository;

import ma.gov.prefagadir.application.backend.models.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("select case when count(p)> 0 then true else false end from Point p where p.lat = :lat and p.lng = :lng")
    boolean existsByLatLng(@Param("lat") double lat, @Param("lng") double lng);

    @Query("SELECT p FROM Point p WHERE p.lat = :lat and p.lng = :lng ")
    Point findByLatLng(@Param("lat") double lat, @Param("lng") double lng);
}
