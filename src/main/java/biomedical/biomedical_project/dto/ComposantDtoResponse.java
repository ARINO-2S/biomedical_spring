package biomedical.biomedical_project.dto;


import biomedical.biomedical_project.entities.Composant;
import biomedical.biomedical_project.entities.Intervention;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ComposantDtoResponse {
    private Integer id;
    private String nomcomposant;
    private double fiabilite;
    private double disponibilite;
    private double dureevie;
    private LocalDate datefonction;
    private double tbf;
    private String nomEquipement;


    public ComposantDtoResponse(Composant composant){
        this.id = composant.getId();
        this.nomcomposant = composant.getNomcomposant();
        this.fiabilite = composant.getFiabilite();
        this.disponibilite = composant.getDisponibilite();
        this.dureevie = composant.getDureevie();
        this.datefonction = composant.getDateFonction();
        this.tbf = composant.getTbf();
        this.nomEquipement = composant.getEquipement().getDesignation();
    }


}
