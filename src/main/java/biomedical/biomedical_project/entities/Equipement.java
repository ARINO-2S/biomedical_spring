package biomedical.biomedical_project.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    private double fiabilite;
    private double disponibilite;


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
    private List<Composant> composants;

    @Transient
    private long dureeUtilisation;





//mtbf
        public void mettreAJourMtbf(double valeur) {
            if (composants == null || composants.isEmpty()) {
                this.mtbf = valeur;  // Si aucun composant, on assigne la valeur donnée
                return;
            }

            double sommeMtbf = 0;
            for (Composant composant : composants) {
                sommeMtbf += composant.getTbf(); // Additionner les tbf des composants existants
            }


            // Calculer la moyenne des tbf
            this.mtbf = sommeMtbf / (composants.size()); // Ajouter 1 pour inclure le nouveau composant
        }


    public void mettreAJourMtbfApresSuppression(double tbfSupprime) {
        if (composants == null || composants.isEmpty()) {
            this.mtbf = 0;  // Aucun composant restant, donc MTBF est nul
            return;
        }

        double sommeMtbf = 0;
        // Soustraire la contribution du composant supprimé
        for (Composant composant : composants) {
            sommeMtbf += composant.getTbf(); // Additionner les tbf des composants restants
        }

        // Soustraire la valeur du tbf du composant supprimé
        sommeMtbf += 2*tbfSupprime + sommeMtbf;

        // Calculer la nouvelle moyenne des tbf après suppression
        this.mtbf = sommeMtbf / composants.size(); // Mise à jour du MTBF
    }




    // fiablite
    public void mettreAJourFiabilite(double valeur) {
        if (composants == null || composants.isEmpty()) {
            this.fiabilite = valeur;  // Si aucun composant, on assigne la valeur donnée
            return;
        }

        double sommeFiabilite = 0;
        for (Composant composant : composants) {
            sommeFiabilite += composant.getFiabilite(); // Additionner les tbf des composants existants
        }


        // Calculer la moyenne des tbf
        this.fiabilite = sommeFiabilite / (composants.size()); // Ajouter 1 pour inclure le nouveau composant
    }


    public void mettreAJourFiabiliteApresSuppression(double fiabiliteSupprime) {
        if (composants == null || composants.isEmpty()) {
            this.fiabilite = 0;  // Aucun composant restant, donc MTBF est nul
            return;
        }

        double sommeFiabilite = 0;
        // Soustraire la contribution du composant supprimé
        for (Composant composant : composants) {
            sommeFiabilite += composant.getFiabilite(); // Additionner les tbf des composants restants
        }

        // Soustraire la valeur du tbf du composant supprimé
        sommeFiabilite += 2*fiabiliteSupprime + sommeFiabilite;

        // Calculer la nouvelle moyenne des tbf après suppression
        this.fiabilite = sommeFiabilite / composants.size(); // Mise à jour du MTBF
    }


    // Disponibilite
    public void mettreAJourDisponibilite(double valeur) {
        if (composants == null || composants.isEmpty()) {
            this.disponibilite = valeur;  // Si aucun composant, on assigne la valeur donnée
            return;
        }

        double sommeDisponibilite = 0;
        for (Composant composant : composants) {
            sommeDisponibilite += composant.getDisponibilite(); // Additionner les tbf des composants existants
        }


        // Calculer la moyenne des tbf
        this.disponibilite = sommeDisponibilite / (composants.size()); // Ajouter 1 pour inclure le nouveau composant
    }


    //disponibilite
    public void mettreAJourDisponibiliteApresSuppression(double disponibiliteSupprime) {
        if (composants == null || composants.isEmpty()) {
            this.disponibilite = 0;  // Aucun composant restant, donc MTBF est nul
            return;
        }

        double sommeDisponibilite = 0;
        // Soustraire la contribution du composant supprimé
        for (Composant composant : composants) {
            sommeDisponibilite += composant.getDisponibilite(); // Additionner les tbf des composants restants
        }

        // Soustraire la valeur du tbf du composant supprimé
        sommeDisponibilite += 2*disponibiliteSupprime + sommeDisponibilite;

        // Calculer la nouvelle moyenne des tbf après suppression
        this.disponibilite = sommeDisponibilite / composants.size(); // Mise à jour du MTBF
    }



    // Méthode pour calculer la durée d'utilisation
    public long getDureeUtilisation() {
        if (dateFonctionnement != null) {
            // Calcul de la différence entre aujourd'hui et la date de mise en fonction
            dureeUtilisation = ChronoUnit.DAYS.between(dateFonctionnement, LocalDate.now());
 //           dureeUtilisation = ChronoUnit.SECONDS.between(dateFonctionnement.atStartOfDay(), LocalDateTime.now());
 //           dureeUtilisation = ChronoUnit.MINUTES.between(dateFonctionnement.atStartOfDay(), LocalDateTime.now());

        } else {
            dureeUtilisation = 0;
        }
        return dureeUtilisation;
    }




}
