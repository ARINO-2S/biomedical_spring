package biomedical.biomedical_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandeDto {
    private String nom;
    private String etablissement;
    private String dateCommande;
    private String materiel;
    private String caracteristiques;
    private int quantite;
    private String local;
    private String telephone;
    private String email;

    // Getters and Setters
}

