package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.dto.EquipementDtoResponse;
import biomedical.biomedical_project.dto.StockGetDTO;
import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Stock;
import biomedical.biomedical_project.repositories.EquipementRepository;
import biomedical.biomedical_project.repositories.StockRepository;
import biomedical.biomedical_project.services.EquipementService;
import biomedical.biomedical_project.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<?> createStock(@RequestBody Stock stock) {
        try {
            // Validation des données
            if (stock.getDateSortie() == null) {
                throw new IllegalArgumentException("La date de sortie est obligatoire.");
            }

            // Validation des données
            if (stock.getDateEntrer() == null) {
                throw new IllegalArgumentException("La date de entrer est obligatoire.");
            }

            // Sauvegarde de l'équipement
            Stock savedStock = stockRepository.save(stock);
            return ResponseEntity.ok(savedStock);
        } catch (Exception e) {
            // Log de l'erreur pour déboguer
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur interne : " + e.getMessage());
        }
    }

    // Méthode GET pour tester la récupération
    @GetMapping
    public List<StockGetDTO> getStock() {
        List<Stock> stock = stockRepository.findAll();
        return stock.stream().map(StockGetDTO::new).toList();
    }

    @PostMapping("/{id}/ajuster-quantite")
    public ResponseEntity<Stock> ajusterQuantite(
            @PathVariable Integer id,
            @RequestParam double valeur) {
        Stock updatedStock = stockService.ajusterQuantite(id, valeur);
        return ResponseEntity.ok(updatedStock);
    }

    @PutMapping("/{id}/ajuster-quantite")
    public ResponseEntity<Stock> ajusterQuantite(@PathVariable Integer id, @RequestBody Map<String, Double> request) {
        double valeur = request.get("valeur");
        Stock stock = stockService.ajusterQuantite(id, valeur);
        return ResponseEntity.ok(stock);
    }





}
