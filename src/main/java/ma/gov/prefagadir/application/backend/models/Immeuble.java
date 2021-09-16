package ma.gov.prefagadir.application.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "immeubles")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "immeuble")
public class Immeuble extends Logement{

    private int numero;

    @Column(name = "nom_ar")
    private String nomAr;

    @Column(name = "nom_fr")
    private String nomFr;

    @Column(name = "nb_appts")
    private int nbAppts;

}
