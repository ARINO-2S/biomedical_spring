package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.dto.EquipementDTO;
import biomedical.biomedical_project.dto.EquipementDtoResponse;
import biomedical.biomedical_project.dto.EquipementInterventionDTO;
import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Fournisseur;
import biomedical.biomedical_project.entities.Intervention;
import biomedical.biomedical_project.repositories.EquipementRepository;
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






//    @PostMapping
//    public ResponseEntity<Equipement> createEquipement(@RequestBody Equipement equipement) {
//        System.out.println("Données reçues : " + equipement);
//        Equipement savedEquipement = equipementRepository.save(equipement);
//        return ResponseEntity.ok(savedEquipement);
//    }

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













    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Integer id) {
        if (equipementRepository.existsById(id)) {
            equipementRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Renvoie 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Renvoie 404 si l'ID n'existe pas
        }
    }


    // Méthode GET pour tester la récupération
    @GetMapping
    public List<EquipementDtoResponse> getEquipements() {
        List<Equipement> equipements = equipementRepository.findAll();
        return equipements.stream().map(EquipementDtoResponse::new).toList();
    }



    // Méthode GET pour récupérer un équipement par ID
    /*@GetMapping("/{Id}")
    public ResponseEntity<EquipementDtoResponse> getEquipementById(@PathVariable Integer Id) {
        Equipement equipement = equipementRepository.findById(Id).orElse(null);

        if (equipement == null) {
            return ResponseEntity.notFound().build();  // Renvoie 404 si l'équipement n'est pas trouvé
        }

        return ResponseEntity.ok(new EquipementDtoResponse(equipement));  // Renvoie l'équipement avec un statut 200 OK
    }*/

    @GetMapping("/{Id}")
    public ResponseEntity<EquipementInterventionDTO> getEquipementById(@PathVariable Integer Id) {
        Equipement equipement = equipementRepository.findById(Id).orElse(null);

        if (equipement == null) {
            return ResponseEntity.notFound().build();  // Renvoie 404 si l'équipement n'est pas trouvé
        }

        return ResponseEntity.ok(new EquipementInterventionDTO(equipement));  // Renvoie l'équipement avec un statut 200 OK
    }

















}

