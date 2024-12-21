package biomedical.biomedical_project.dto;

import biomedical.biomedical_project.entities.Composant;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ComposantDtoForEquipement {
    private Integer id;
    private String nomcomposant;
    private double tbf;
    private double fiabilite;
    private String marque;
    private String sn;
    private long dureeUtilisation;

    public ComposantDtoForEquipement(Composant composant){
        this.id = composant.getId();
        this.nomcomposant = composant.getNomcomposant();
        this.tbf = composant.getTbf();
        this.fiabilite = composant.getFiabilite();
        this.marque = composant.getMarque();
        this.sn = composant.getSn();
        this.dureeUtilisation = composant.getDureeUtilisation();
    }


}
