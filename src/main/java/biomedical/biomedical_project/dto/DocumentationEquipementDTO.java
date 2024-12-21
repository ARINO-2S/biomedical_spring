package biomedical.biomedical_project.dto;

import biomedical.biomedical_project.entities.Documentation;
import biomedical.biomedical_project.enums.InterventionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DocumentationEquipementDTO {
    private Integer id;
    private byte[] fileData;
    private String nom;
    private String type;


    public DocumentationEquipementDTO(Documentation documentation) {
        this.id = documentation.getId();
        this.fileData = documentation.getFileData();
        this.nom = documentation.getNom();
        this.type = documentation.getType();

    }

}
