package biomedical.biomedical_project.dto;


import biomedical.biomedical_project.entities.Documentation;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentationDtoForEquipement {
    private Integer id;
    private String type;
    private String nom;


    public DocumentationDtoForEquipement (Documentation documentation){
        this.type = documentation.getType();
        this.nom = documentation.getNom();
        this.id = documentation.getId();

    }
}
