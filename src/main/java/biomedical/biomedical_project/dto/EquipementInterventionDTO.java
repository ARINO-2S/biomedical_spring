package biomedical.biomedical_project.dto;

import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.enums.InterventionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EquipementInterventionDTO {
    private Integer id;
    private String nom;
    private String marque;
    private String modele;
    private String sn;
    private String service;
    private LocalDate dateFonctionnement;
    private Integer garantie;
  // private String nomIntervention;
    List<InterventioDtoForEquipement> interventions;

    public EquipementInterventionDTO(Equipement equipement){

        this.id = equipement.getId();
        this.nom= equipement.getDesignation();
        this.marque= equipement.getMarque();
        this.modele= equipement.getModele();
        this.sn= equipement.getSn();
        this.service= equipement.getService();
        this.garantie= equipement.getGarantie();
        this.dateFonctionnement = equipement.getDateFonctionnement();
        this.interventions = equipement.getInterventions().stream().map(InterventioDtoForEquipement::new).toList();


//        this.interventions = equipement.getInterventions().stream().map(intervention -> {
//            return new InterventioDtoForEquipement(intervention.getType());
//        }).toList();
//
//
//        this.interventions = equipement.getInterventions().stream().map(intervention -> {
//            return new InterventioDtoForEquipement(intervention.getDate());
//        }).toList();


    }



}
