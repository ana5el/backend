package ma.gov.prefagadir.application.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "aal")
public class AAL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "label_ar")
    private String labelAr;

    @Column(name = "label_fr")
    private String labelFr;

    @Column(name = "tel")
    private String tel;

    @OneToOne
    @JoinColumn(name = "type_all_id")
    private  TypeAAL typeAAL;

    @OneToOne
    @JoinColumn(name = "sup_id")
    private AAL aal;

    @OneToOne
    @JoinColumn(name = "zone_geo")
    private Zone zone;

    public AAL(String labelAr, String labelFr, String tel, TypeAAL typeAAL, AAL aal, Zone zone) {
        this.labelAr = labelAr;
        this.labelFr = labelFr;
        this.tel = tel;
        this.typeAAL = typeAAL;
        this.aal = aal;
        this.zone = zone;
    }
}
