package biomedical.biomedical_project.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private LocalTime heureArrivé;
    private LocalTime heureDépart;
    private Double dureeTrajet;
    private Character Type;
    private String texte;
    private Double Frais;
    private String nomClient;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Equipement equipement;
    @OneToMany(mappedBy = "intervention")
    private List<PieceDeRechange> pieceDeRechangeList;
    @OneToMany(mappedBy = "intervention")
    private List<TPlanning> planningList;
}
