package biomedical.biomedical_project.dto;


import biomedical.biomedical_project.entities.Composant;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ComposantEquipementDTO {

    private Integer equipementId;
    private String nomcomposant;
    private double fiabilite;
    private double disponibilite;
    private double dureevie;
    private LocalDate datefonction;
    private double tbf;
    private String marque;
    private String sn;




}
