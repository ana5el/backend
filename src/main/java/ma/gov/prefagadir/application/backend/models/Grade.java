package ma.gov.prefagadir.application.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label_ar")
    private String labelAr;

    @Column(name = "label_fr")
    private String labelFr;

    public Grade(String labelFr, String labelAr){
        this.labelFr = labelFr;
        this.labelAr = labelAr;
    }



}
