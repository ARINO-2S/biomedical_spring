package biomedical.biomedical_project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Entity
@Getter
@Setter
public class Documentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private String nom;


    @Lob
    private byte[] fileData;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Equipement equipement;

    @ManyToOne
    private Fournisseur fournisseur;

    @ManyToOne
    private Intervention intervention;


    // Méthode pour réinitialiser ou supprimer les dépendances
    public void resetDependances() {
        // Réinitialiser les valeurs dépendantes
        this.equipement = null;
    }

}

