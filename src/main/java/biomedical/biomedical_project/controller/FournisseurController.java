package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.entities.Fournisseur;
import biomedical.biomedical_project.repositories.FournisseurRepository;
import biomedical.biomedical_project.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @Autowired
    private FournisseurRepository fournisseurRepository; // Injection du service uniquement

    // Méthode POST pour créer un fournisseur
    @PostMapping
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur) {
        System.out.println("Données reçues : " + fournisseur);
        Fournisseur savedFournisseur = fournisseurService.createFournisseur(fournisseur);  // Utilisation du service
        return ResponseEntity.ok(savedFournisseur);
    }

    // Méthode DELETE pour supprimer un fournisseur par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Integer id) {
        boolean deleted = fournisseurService.deleteFournisseur(id);  // Utilisation du service
        if (deleted) {
            return ResponseEntity.noContent().build(); // Renvoie 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Renvoie 404 si l'ID n'existe pas
        }
    }

    // Méthode GET pour récupérer un fournisseur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable Integer id) {
        Fournisseur fournisseur = fournisseurService.getFournisseurById(id);  // Utilisation du service

        if (fournisseur == null) {
            return ResponseEntity.notFound().build();  // Renvoie 404 si le fournisseur n'est pas trouvé
        }

        return ResponseEntity.ok(fournisseur);  // Renvoie le fournisseur avec un statut 200 OK
    }

    // Méthode GET pour récupérer tous les fournisseurs
    @GetMapping
    public ResponseEntity<List<Fournisseur>> getAllFournisseurs() {
        List<Fournisseur> fournisseurs = fournisseurService.getAllFournisseurs();  // Récupération de tous les fournisseurs via le service
        if (fournisseurs.isEmpty()) {
            return ResponseEntity.noContent().build(); // Renvoie 204 No Content si la liste est vide
        }
        return ResponseEntity.ok(fournisseurs);  // Renvoie la liste des fournisseurs
    }

//    @GetMapping
//    public ResponseEntity<?> getFournisseurs() {
//        return ResponseEntity.ok(fournisseurRepository.findAll());
//    }
}
