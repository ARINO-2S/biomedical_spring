package biomedical.biomedical_project.dto;

import biomedical.biomedical_project.entities.Intervention;
import biomedical.biomedical_project.enums.InterventionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InterventionDtoResponse {
    private Long id;
    private String nom;
    private InterventionType type;
    private String action;
    private LocalDate date;
    private String nomEquipement;

    public InterventionDtoResponse(Intervention intervention){
        this.id = intervention.getId();
        this.nom = intervention.getNom();
        this.type = intervention.getType();
        this.action = intervention.getAction();
        this.date = intervention.getDate();
        this.nomEquipement = intervention.getEquipement().getDesignation();
    }

}
