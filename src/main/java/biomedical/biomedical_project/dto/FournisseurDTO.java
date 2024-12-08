package biomedical.biomedical_project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
public class FournisseurDTO {
    private String nomfournisseur;  // Le nom du fournisseur
    private String adresse;         // Adresse du fournisseur
    private String contact;         // Contact du fournisseur
    private double fiabilite;       // Fiabilité du fournisseur
    private double disponibilite;   // Disponibilité du fournisseur
    private double dureevie;        // Durée de vie estimée des produits du fournisseur




    public FournisseurDTO(String nomfournisseur) {
        this.nomfournisseur= nomfournisseur;
    }




    // Getters et Setters
    public String getNomFournisseur() {
        return nomfournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomfournisseur = nomFournisseur;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getFiabilite() {
        return fiabilite;
    }

    public void setFiabilite(double fiabilite) {
        this.fiabilite = fiabilite;
    }

    public double getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(double disponibilite) {
        this.disponibilite = disponibilite;
    }

    public double getDureevie() {
        return dureevie;
    }

    public void setDureevie(double dureevie) {
        this.dureevie = dureevie;
    }
}
