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
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "logement")
public class Logement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private double superficie;
    private String rue;
    private String quartier;


    @OneToOne(cascade = CascadeType.ALL)
    private Zone zone;

}
