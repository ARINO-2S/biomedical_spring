package biomedical.biomedical_project.dto;


import biomedical.biomedical_project.entities.Composant;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ComposantEquipementDTO {
    private String nomcomposant;
    private double fiabilite;
    private double disponibilite;
    private double dureevie;
    private Date datefonction;
    private double tbf;

    public ComposantEquipementDTO(Composant composant) {


    }

}
