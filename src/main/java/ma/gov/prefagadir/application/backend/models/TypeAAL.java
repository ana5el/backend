package ma.gov.prefagadir.application.backend.models;

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
@Table(name = "type_all")
public class TypeAAL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_all_id")
    private Integer id;

    @Column(name = "label_ar")
    private String labelAr;

    @Column(name = "label_fr")
    private String labelFr;
}
