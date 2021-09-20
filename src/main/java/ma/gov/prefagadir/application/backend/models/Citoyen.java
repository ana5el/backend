package ma.gov.prefagadir.application.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Citoyen {
    @Id
    private String  cin;
    @Column(name = "date_naissance")
    private Date dateNaissance;
    @Column(name = "lieu_naissance")
    private String lieuNaissance;
    private String nomMereAr;
    private String nomMereFr;
    private String nomPereFr;
    private String nomPereAr;
    private String situationFamiliale;
    private String nomAr;
    private String prenomAr;
    private String nomFr;
    private String prenomFr;
    @OneToOne
    private Profession profession;



}
