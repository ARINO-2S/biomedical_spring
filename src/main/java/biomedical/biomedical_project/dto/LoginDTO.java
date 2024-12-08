package biomedical.biomedical_project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter// Lombok - Génère automatiquement les getters et setters
public class LoginDTO {

    private String username;
    private String password;
}

