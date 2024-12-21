package biomedical.biomedical_project.dto;


import biomedical.biomedical_project.entities.Documentation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class DocumentationDTO {

    private Integer id;
    private String type;
    private byte[] fileData;
    private String nom;
    private Integer equipementId;


//    public void documentationOpt(Documentation documentation){
//
//    }


}
