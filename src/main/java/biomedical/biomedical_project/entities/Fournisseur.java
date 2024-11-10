package biomedical.biomedical_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String tele;
    private String email;
    private String adresse;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fournisseur")
    private List<Equipement> equipements;
    @OneToMany(mappedBy = "fournisseur")
    private List<Documentation> documentations;
}
