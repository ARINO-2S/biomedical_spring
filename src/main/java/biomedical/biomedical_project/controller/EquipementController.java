package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.dto.*;
import biomedical.biomedical_project.entities.*;
import biomedical.biomedical_project.repositories.ComposantRepository;
import biomedical.biomedical_project.repositories.DocumentationRepository;
import biomedical.biomedical_project.repositories.EquipementRepository;
import biomedical.biomedical_project.repositories.InterventionRepository;
import biomedical.biomedical_project.services.EquipementService;
import biomedical.biomedical_project.services.FournisseurService;
import biomedical.biomedical_project.services.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/equipement")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EquipementController {

    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private ComposantRepository composantRepository;

    @Autowired
    private InterventionRepository interventionRepository;

    @Autowired
    private DocumentationRepository documentationRepository;

    @Autowired
    private EquipementService equipementService;


    @Autowired
    private FournisseurService fournisseurService;





    @Autowired
    private InterventionService interventionService;

    @GetMapping("/equipments-list")
    public List<Equipement> getEquipments() {
        return equipementService.getAllEquipements();
    }

    @GetMapping("/fournisseurs-list")
    public List<Fournisseur> getSuppliers() {
        return fournisseurService.getAllFournisseurs();
    }

    @GetMapping("/interventions-list")
    public List<Intervention> getInterventions() {
        return interventionService.getAllInterventions();
    }






    @PostMapping
    public ResponseEntity<?> createEquipement(@RequestBody Equipement equipement) {
        try {
            // Validation des données
            if (equipement.getDateFonctionnement() == null) {
                throw new IllegalArgumentException("La date de mise est obligatoire.");
            }

            // Sauvegarde de l'équipement

            Equipement savedEquipement = equipementRepository.save(equipement);
            return ResponseEntity.ok(savedEquipement);
        } catch (Exception e) {
            // Log de l'erreur pour déboguer
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur interne : " + e.getMessage());
        }
    }













//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEquipement(@PathVariable Integer id) {
//        if (equipementRepository.existsById(id)) {
//            equipementRepository.deleteById(id);
//            return ResponseEntity.noContent().build(); // Renvoie 204 No Content
//        } else {
//            return ResponseEntity.notFound().build(); // Renvoie 404 si l'ID n'existe pas
//        }
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Integer id) {
        // Vérifier si l'équipement existe
        if (!equipementRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404 si l'équipement n'existe pas
        }

        // Récupérer les composantes liées à l'équipement
        List<Composant> composantes = composantRepository.findByEquipementId(id);

        // Traiter chaque composante avant suppression (si nécessaire)
        for (Composant composante : composantes) {
            // Réinitialiser les valeurs ou supprimer les relations dépendantes
            composante.resetDependances();
            composantRepository.delete(composante);
        }

        // Récupérer les interventions liées à l'équipement
        List<Intervention> interventions = interventionRepository.findByEquipementId(id);

        // Traiter chaque interevention avant suppression (si nécessaire)
        for (Intervention intervention : interventions) {
            // Réinitialiser les valeurs ou supprimer les relations dépendantes
            intervention.resetDependances();
            interventionRepository.delete(intervention);
        }

        // Récupérer les composantes liées à l'équipement
        List<Documentation> documentations = documentationRepository.findByEquipementId(id);

        // Traiter chaque composante avant suppression (si nécessaire)
        for (Documentation documentation : documentations) {
            // Réinitialiser les valeurs ou supprimer les relations dépendantes
            documentation.resetDependances();
            documentationRepository.save(documentation);
        }










        // Supprimer l'équipement
        equipementRepository.deleteById(id);

        return ResponseEntity.noContent().build(); // 204 No Content
    }















    // Méthode GET pour tester la récupération
    @GetMapping
    public List<EquipementDtoResponse> getEquipements() {
        List<Equipement> equipements = equipementRepository.findAll();
        return equipements.stream().map(EquipementDtoResponse::new).toList();
    }



//// well done "intervention && composant "  --> still need to add  " Fournisseur "
//    @GetMapping("/{Id}")
//    public ResponseEntity<EquipementComposantDTO> getEquipementById(@PathVariable Integer Id) {
//        Equipement equipement = equipementRepository.findById(Id).orElse(null);
//
//        if (equipement == null) {
//            return ResponseEntity.notFound().build();  // Renvoie 404 si l'équipement n'est pas trouvé
//        }
//
//        return ResponseEntity.ok(new EquipementComposantDTO(equipement));  // Renvoie l'équipement avec un statut 200 OK
//    }




    // well done "intervention && composant "  --> still need to add  " Fournisseur "
    @GetMapping("/{Id}")
    public ResponseEntity<EquipementAllDTO> getEquipementById(@PathVariable Integer Id) {
        Equipement equipement = equipementRepository.findById(Id).orElse(null);

        if (equipement == null) {
            return ResponseEntity.notFound().build();  // Renvoie 404 si l'équipement n'est pas trouvé
        }

        return ResponseEntity.ok(new EquipementAllDTO(equipement));  // Renvoie l'équipement avec un statut 200 OK
    }









}

