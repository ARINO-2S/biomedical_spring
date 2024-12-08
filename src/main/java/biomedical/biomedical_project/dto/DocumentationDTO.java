package biomedical.biomedical_project.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class DocumentationDTO {

    private Integer id;
    private String type;
    private byte[] file;
    private String format;
    private String nom;


//    public DocumentationDto(Documentation documentation){
//
//    }


}
