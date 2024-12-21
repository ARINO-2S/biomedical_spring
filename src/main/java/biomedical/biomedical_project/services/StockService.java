package biomedical.biomedical_project.services;

import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Stock;
import biomedical.biomedical_project.repositories.EquipementRepository;
import biomedical.biomedical_project.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockService {
    @Autowired
    private StockRepository repository;


    public List<Stock> getStock() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    public Stock ajusterQuantite(Integer stockId, double valeur) {
        Stock stock = repository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock introuvable avec l'ID : " + stockId));

        stock.mettreAJourQuantite(valeur);
        return repository.save(stock);
    }




}
