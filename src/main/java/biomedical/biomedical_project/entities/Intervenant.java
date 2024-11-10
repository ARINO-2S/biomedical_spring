package biomedical.biomedical_project.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Intervenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;
    private String tele;
    private String email;
    private String fonction;
    private Double coutHoraireInterventon;
    @OneToMany(mappedBy = "intervenant")
    private List<TPlanning> tPlanningList;

}
