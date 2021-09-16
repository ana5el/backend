package ma.gov.prefagadir.application.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POINT_ID")
    private Long id;

    private double lat;
    private double lng;

    @OneToMany(mappedBy = "point")
    @JsonIgnoreProperties("point")
    private Set<ZonePoint> zonePoints = new HashSet<>();

    public Point(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

}
