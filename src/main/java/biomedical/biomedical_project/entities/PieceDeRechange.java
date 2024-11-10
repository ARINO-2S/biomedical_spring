package biomedical.biomedical_project.entities;

import jakarta.persistence.*;

@Entity
public class PieceDeRechange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Intervention intervention;
    @ManyToOne
    private GestionDeStock gestionDeStock;
}
