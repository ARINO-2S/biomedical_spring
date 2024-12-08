package biomedical.biomedical_project.entities;

import biomedical.biomedical_project.enums.InterventionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
@ToString

public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom ne peut pas être nul")
    private String nom;


    //@NotNull(message = "Le type ne peut pas être nul")

    private InterventionType type;

    @Size(min = 3, message = "L'action doit avoir au moins 3 caractères")
    private String action;

    private LocalDate date;

    @ManyToOne
    private Client client;
    @ManyToOne
    private Equipement equipement;

    @OneToMany(mappedBy = "intervention")
    private List<Composant> composantList;

    @OneToMany(mappedBy = "intervention")
    private List<TPlanning> planningList;

    public void setCategorie(String categorie) {
    }
}
