package biomedical.biomedical_project.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TPlanning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Intervention intervention;
    @ManyToOne
    private Intervenant intervenant;
}
