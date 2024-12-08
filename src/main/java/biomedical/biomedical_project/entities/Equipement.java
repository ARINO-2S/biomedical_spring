package biomedical.biomedical_project.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
@ToString
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String designation;
    private String nomClient;
    private String categorie;
    private Integer garantie;
    private String marque;
    private String modele;
    private String sn;//numero de serie
    private String service;
    private String fournisseur;
    private double mtbf;


   /* @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateFonctionnement;*/
    private LocalDate dateFonctionnement;

    @ManyToOne
    private Fournisseur fournisseurId;
    @OneToMany(mappedBy = "equipement")
    private List<Intervention> interventions;
    @OneToMany(mappedBy = "equipement")
    private List<Documentation> documentations;
    @OneToMany(mappedBy = "equipement")
    private List<Composant> composant;

}
