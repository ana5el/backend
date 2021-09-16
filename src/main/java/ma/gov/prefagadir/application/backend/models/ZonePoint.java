package ma.gov.prefagadir.application.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "zones_points")
public class ZonePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "zone_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "zonePoints"})
    private Zone zone;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "point_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "zonePoints"})
    private Point point;
    @Column(name = "order_in_polygon")
    private Integer order;
}
