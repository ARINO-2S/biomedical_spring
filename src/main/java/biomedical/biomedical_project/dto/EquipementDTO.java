package biomedical.biomedical_project.dto;

import biomedical.biomedical_project.entities.Equipement;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class EquipementDTO {
    private String nom;
    private String fournisseur;
    private String marque;
    private String modele;
    private String numeroSerie;
    private String service;
    private Date dateFonctionnement;
    private String type;
    private Integer fournisseurId;
    private double mtbf;
    private List<ComposantDTO> composantes;


    public EquipementDTO(String nom) {
        this.nom= nom;
    }



}
