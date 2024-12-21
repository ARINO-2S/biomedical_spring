package biomedical.biomedical_project.dto;

import biomedical.biomedical_project.entities.Personnel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonnelDTO {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String tele;
    private String role;
    public PersonnelDTO(Personnel personnel) {

        this.id = personnel.getId();
        this.nom = personnel.getNom();
        this.prenom = personnel.getPrenom();
        this.email = personnel.getEmail();
        this.tele = personnel.getTele();
        this.role = personnel.getRole();

    }
}
