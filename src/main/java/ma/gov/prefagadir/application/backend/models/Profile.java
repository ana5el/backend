package ma.gov.prefagadir.application.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "label_ar")
    private String labelAr;

    @Column(name = "label_fr")
    private String labelFr;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "profile_privilege", joinColumns = {@JoinColumn( name = "profile_id")}, inverseJoinColumns ={@JoinColumn(name = "privilege_id")})
    private Set<Privilege> privileges = new HashSet<>();

    public Profile(String name, String labelAr, String labelFr) {
        this.name = name;
        this.labelAr = labelAr;
        this.labelFr = labelFr;
    }
}
