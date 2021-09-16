package ma.gov.prefagadir.application.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AgentAutorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cin;
    private String nom;
    private String prenom;
    private String tel;
    @OneToOne
    @JoinColumn(name = "id_grade")
    private  Grade grade;

    @OneToOne
    @JoinColumn(name ="aal_id")
    private AAL aal;

    @OneToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    public AgentAutorite(String cin, String nom, String prenom, String tel, Grade grade) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.grade = grade;
    }
}
