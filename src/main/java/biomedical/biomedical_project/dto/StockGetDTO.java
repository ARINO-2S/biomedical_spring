package biomedical.biomedical_project.dto;

import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Stock;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockGetDTO {
    private Integer id;
    private String nom;
    private String marque;
    private double quantite;
    private String modele;
    private double dureeVie;
    private LocalDate dateSortie;
    private LocalDate dateEntrer;

    public StockGetDTO(Stock stock) {
        this.id = stock.getId();
        this.nom= stock.getNom();
        this.marque= stock.getMarque();
        this.modele= stock.getModele();
        this.quantite= stock.getQuantite();
        this.dureeVie= stock.getDureeVie();
        this.dateEntrer= stock.getDateEntrer();

    }
}
