package biomedical.biomedical_project.dto;


import biomedical.biomedical_project.entities.Equipement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;

@Getter
@Setter
public class EquipementDtoResponse{
    private Integer id;
    private String nom;
    private String marque;
    private String modele;
    private String sn;
    private String service;
    private LocalDate dateFonctionnement;
    private Integer garantie;
    private double fiabilite;
    private double disponibilite;
    private double mtbf;
    private long dureeUtilisation;

    public EquipementDtoResponse(Equipement equipement) {
        this.id = equipement.getId();
        this.nom= equipement.getDesignation();
        this.marque= equipement.getMarque();
        this.modele= equipement.getModele();
        this.sn= equipement.getSn();
        this.service= equipement.getService();
        this.garantie= equipement.getGarantie();
        this.dateFonctionnement = equipement.getDateFonctionnement();
        this.fiabilite= equipement.getFiabilite();
        this.disponibilite= equipement.getDisponibilite();
        this.mtbf= equipement.getMtbf();
        this.dureeUtilisation= equipement.getDureeUtilisation();
    }
}
