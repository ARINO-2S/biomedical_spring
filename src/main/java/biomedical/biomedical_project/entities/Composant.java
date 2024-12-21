package biomedical.biomedical_project.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
@ToString
public class Composant {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Integer id;
        private String nomcomposant;
        private double fiabilite;
        private double disponibilite;
        private double dureevie;
        private double tbf;
        private String marque;
        private String sn;

        @Transient
        private long dureeUtilisation;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate datefonction;

        @ManyToOne
        private Fournisseur Composant;
        @ManyToOne
        private Equipement equipement;
        @ManyToOne
        private GestionDeStock gestionDeStock;
        @ManyToOne
        private Intervention intervention;




        public LocalDate getDateFonction() {
                return datefonction;
        }

        public void setDateFonction(LocalDate datefonction) {
                this.datefonction = datefonction;
        }


        // Méthode pour réinitialiser ou supprimer les dépendances
        public void resetDependances() {
                // Réinitialiser les valeurs dépendantes
                this.fiabilite = 0.0;
                this.tbf = 0.0;
                // Supprimer toute autre relation si nécessaire
        }


        // Méthode pour calculer la durée d'utilisation
        public long getDureeUtilisation() {
                if (datefonction != null) {
                        // Calcul de la différence entre aujourd'hui et la date de mise en fonction
                        dureeUtilisation = ChronoUnit.DAYS.between(datefonction, LocalDate.now());
                        //           dureeUtilisation = ChronoUnit.SECONDS.between(dateFonctionnement.atStartOfDay(), LocalDateTime.now());
                        //           dureeUtilisation = ChronoUnit.MINUTES.between(dateFonctionnement.atStartOfDay(), LocalDateTime.now());

                } else {
                        dureeUtilisation = 0;
                }
                return dureeUtilisation;
        }




}
