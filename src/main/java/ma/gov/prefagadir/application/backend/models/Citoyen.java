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
    @OneToOne
    @JoinColumn(name = "cin_pere")
    private Citoyen pere;
    @OneToOne
    @JoinColumn(name = "cin_mere")
    private Citoyen mere;
    private String situationFamiliale;
    private String nomAr;
    private String prenomAr;
    private String nomFr;
    private String prenomFr;
    private String profession;


}
