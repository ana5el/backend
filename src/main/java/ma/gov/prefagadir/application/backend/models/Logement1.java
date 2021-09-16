package ma.gov.prefagadir.application.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Logement1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String info;
    private String rue;
    private double superficie;

    @OneToOne
    @JoinColumn(name = "type_logement")
    private TypeLogement type;

    @OneToOne
    @JoinColumn(name = "quartier")
    private Quartier quartier;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "logements"})
    private Zone zone;

    public Logement1(String info, String rue, double superficie, TypeLogement type, Quartier quartier, Zone zone) {
        this.info = info;
        this.rue = rue;
        this.superficie = superficie;
        this.type = type;
        this.quartier = quartier;
        this.zone = zone;
    }
}
