package biomedical.biomedical_project.dto;

import biomedical.biomedical_project.entities.Documentation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {

    private byte[] fileData;


    public ImageDTO(Documentation documentation) {

        this.fileData = documentation.getFileData();


    }
}
