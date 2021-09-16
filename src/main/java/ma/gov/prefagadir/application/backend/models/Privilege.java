package ma.gov.prefagadir.application.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "label_ar")
    private String labelAr;

    @Column(name = "label_fr")
    private String labelFr;

    private String description;

    public Privilege(String name, String labelAr, String labelFr, String description) {
        this.name = name;
        this.labelAr = labelAr;
        this.labelFr = labelFr;
        this.description = description;
    }

}
