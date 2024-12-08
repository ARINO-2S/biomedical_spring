package biomedical.biomedical_project.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class GestionDeStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInstr;
    private String designationInstr;
    private String typeInstr;
    private Integer quantityInstr;
    private Integer quantityResv;
    private Double priceInstr;
    private LocalDateTime dateReception;
    @OneToMany(mappedBy = "gestionDeStock")
    private List<Composant> composantList;


}
