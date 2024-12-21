package biomedical.biomedical_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;
    private String tele;
    private String email;
    private String role;


}
