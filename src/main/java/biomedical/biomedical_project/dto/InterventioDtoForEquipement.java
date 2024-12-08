package biomedical.biomedical_project.dto;

import biomedical.biomedical_project.entities.Intervention;
import biomedical.biomedical_project.enums.InterventionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InterventioDtoForEquipement {
    private InterventionType type;
    private LocalDate date;
    private String action;

    public InterventioDtoForEquipement(Intervention intervention) {
        this.type = intervention.getType();
        this.date = intervention.getDate();
        this.action = intervention.getAction();
    }

//    public InterventioDtoForEquipement(InterventionType intervention) {
//        this.type = intervention;
//
//    }
//
//    public InterventioDtoForEquipement(LocalDate date) {
//        this.date = date;
//
//    }

}
