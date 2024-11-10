package biomedical.biomedical_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String ville;
    private String tele;
    private String email;
    private String serviceClient;
    @OneToMany(mappedBy = "client")
    private List<Intervention> interventions;
    @OneToMany(mappedBy = "client")
    private List<Documentation> documentations;


}
