package ma.gov.prefagadir.application.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "type_logement")
public class TypeLogement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "libelle_ar")
    private String libelleAr;
    @Column(name = "libelle_fr")
    private String libelleFr;
}
