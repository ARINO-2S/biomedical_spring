package biomedical.biomedical_project.dto;

import biomedical.biomedical_project.enums.InterventionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
public class InterventionDTO {
    private LocalDate date;
    private Integer equipementId;
    private String nom;
    private InterventionType type;
    private String action;

}
