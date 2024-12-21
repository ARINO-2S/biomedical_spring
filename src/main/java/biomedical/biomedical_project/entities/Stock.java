package biomedical.biomedical_project.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;



@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
@ToString
public class Stock {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String marque;
    private double quantite;
    private String modele;
    private double dureeVie;
    private LocalDate dateSortie;
    private LocalDate dateEntrer;


    /**
     * Met à jour la quantité du stock en fonction de la valeur donnée.
     * @param valeur la valeur à ajouter (positive) ou à retrancher (négative).
     * @throws IllegalArgumentException si la quantité devient négative.
     */
    public void mettreAJourQuantite(double valeur) {
        double nouvelleQuantite = this.quantite + valeur;
        if (nouvelleQuantite < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative !");
        }
        this.quantite = nouvelleQuantite;
    }



}
