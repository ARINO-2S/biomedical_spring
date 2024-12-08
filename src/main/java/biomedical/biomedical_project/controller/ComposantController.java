package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.entities.Composant;

import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.repositories.ComposantRepository;
import biomedical.biomedical_project.services.ComposantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/composant")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ComposantController {

    @Autowired
    private ComposantRepository composantRepository;

    @Autowired
    private ComposantService composantService;

//    @PostMapping
//    public ResponseEntity<Composant> createComposant(@RequestBody Composant composant) {
//        System.out.println("Données reçues : " + composant);
//        Composant savedComposant = composantRepository.save(composant);
//        return ResponseEntity.ok(savedComposant);
//    }


    @PostMapping
    public ResponseEntity<?> createComposant(@RequestBody Composant composant) {
        try {
            // Validation des données
            if (composant.getDateFonction() == null) {
                throw new IllegalArgumentException("La date de mise est obligatoire.");
            }

            // Sauvegarde de l'équipement
            Composant savedComposant = composantRepository.save(composant);
            return ResponseEntity.ok(savedComposant);
        } catch (Exception e) {
            // Log de l'erreur pour déboguer
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur interne : " + e.getMessage());
        }
    }







    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComposant(@PathVariable Integer id) {
        if (composantRepository.existsById(id)) {
            composantRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Renvoie 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Renvoie 404 si l'ID n'existe pas
        }
    }

    // Méthode GET pour récupérer un composant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Composant> getComposantById(@PathVariable Integer id) {
        Composant composant = composantRepository.findById(id).orElse(null);

        if (composant == null) {
            return ResponseEntity.notFound().build();  // Renvoie 404 si le composant n'est pas trouvé
        }

        return ResponseEntity.ok(composant);  // Renvoie le composant avec un statut 200 OK
    }

    // Méthode GET pour récupérer tous les fournisseurs
    @GetMapping
    public ResponseEntity<List<Composant>> getAllComposants() {
        List<Composant> composants = composantService.getAllComposants();  // Récupération de tous les fournisseurs via le service
        if (composants.isEmpty()) {
            return ResponseEntity.noContent().build(); // Renvoie 204 No Content si la liste est vide
        }

        return ResponseEntity.ok(composants);
    }


}
