package biomedical.biomedical_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@Setter
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
    @OneToMany(mappedBy = "equipement")
    private List<Intervention> interventions;
    @OneToMany(mappedBy = "equipement")
    private List<Documentation> documentations;
    @ManyToOne
    private Fournisseur fournisseur;



}
