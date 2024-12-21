package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.dto.ComposantDtoResponse;
import biomedical.biomedical_project.dto.ComposantEquipementDTO;
import biomedical.biomedical_project.dto.InterventionDTO;
import biomedical.biomedical_project.dto.InterventionDtoResponse;
import biomedical.biomedical_project.entities.Composant;

import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Intervention;
import biomedical.biomedical_project.repositories.ComposantRepository;
import biomedical.biomedical_project.repositories.EquipementRepository;
import biomedical.biomedical_project.services.ComposantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/composant")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ComposantController {

    @Autowired
    private ComposantRepository composantRepository;

    @Autowired
    private ComposantService composantService;

    @Autowired
    private EquipementRepository equipementRepository;

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






//////////////////////////////////////////
//@DeleteMapping("/{id}")
//public ResponseEntity<Void> deleteComposant(@PathVariable Integer id) {
//    if (composantRepository.existsById(id)) {
//        composantRepository.deleteById(id);
//        return ResponseEntity.noContent().build(); // Retour 204 si suppression réussie
//    } else {
//        return ResponseEntity.notFound().build(); // Retour 404 si composant non trouvé
//    }
//}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComposant(@PathVariable Integer id) {
        if (composantRepository.existsById(id)) {
            // Récupérer le composant à supprimer
            Composant composant = composantRepository.findById(id).orElse(null);

            if (composant != null) {
                // Récupérer l'équipement associé au composant
                Equipement equipement = composant.getEquipement();
                if (equipement != null) {
                    // Retirer la contribution du composant du calcul de MTBF
                    equipement.mettreAJourMtbfApresSuppression(-composant.getTbf()); // Passer la valeur négative pour soustraire la contribution du composant supprimé
                    equipement.mettreAJourFiabiliteApresSuppression(-composant.getFiabilite());
                    equipement.mettreAJourDisponibiliteApresSuppression(-composant.getDisponibilite());

                    equipementRepository.save(equipement); // Sauvegarder l'équipement mis à jour
                }
            }

            // Supprimer le composant
            composantRepository.deleteById(id);

            return ResponseEntity.noContent().build(); // Retour 204 si suppression réussie
        } else {
            return ResponseEntity.notFound().build(); // Retour 404 si composant non trouvé
        }
    }


//////////////////////////////////////////

    // Méthode GET pour récupérer un composant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Composant> getComposantById(@PathVariable Integer id) {
        Composant composant = composantRepository.findById(id).orElse(null);

        if (composant == null) {
            return ResponseEntity.notFound().build();  // Renvoie 404 si le composant n'est pas trouvé
        }

        return ResponseEntity.ok(composant);  // Renvoie le composant avec un statut 200 OK
    }

//    // Méthode GET pour récupérer tous les fournisseurs
//    @GetMapping
//    public ResponseEntity<List<Composant>> getAllComposants() {
//        List<Composant> composants = composantService.getAllComposants();  // Récupération de tous les fournisseurs via le service
//        if (composants.isEmpty()) {
//            return ResponseEntity.noContent().build(); // Renvoie 204 No Content si la liste est vide
//        }
//
//        return ResponseEntity.ok(composants);
//    }

    @GetMapping
    public List<ComposantDtoResponse> getComposant() {
        List<Composant> composantList =  composantRepository.findAll();
        return composantList.stream().map(ComposantDtoResponse::new).toList();

    }


//    @PostMapping("/addNew")
//    public ResponseEntity<Void> addNewComposant(@RequestBody ComposantEquipementDTO composantEquipementDTO) {
//        Composant composant = new Composant();
//        composant.setNomcomposant(composantEquipementDTO.getNomcomposant());
//        composant.setFiabilite(composantEquipementDTO.getFiabilite());
//        composant.setDisponibilite(composantEquipementDTO.getDisponibilite());
//        composant.setDureevie(composantEquipementDTO.getDureevie());
//        composant.setDateFonction(composantEquipementDTO.getDatefonction());
//        composant.setTbf(composantEquipementDTO.getTbf());
//
//        Equipement equipement = equipementRepository.findById(composantEquipementDTO.getEquipementId())
//                .orElse(null);
//
//        composant.setEquipement(equipement);
//        composantRepository.save(composant);
//        assert equipement != null;
//        if(equipement.getComposants() != null) equipement.getComposants().add(composant);
//        else equipement.setComposants(Collections.singletonList(composant));
//        equipementRepository.save(equipement);
//        return new ResponseEntity<>(HttpStatus.OK);
//
//
//
//    }




    @PostMapping("/addNew")
    public ResponseEntity<Void> addNewComposant(@RequestBody ComposantEquipementDTO composantEquipementDTO) {
        Composant composant = new Composant();
        composant.setNomcomposant(composantEquipementDTO.getNomcomposant());
        composant.setFiabilite(composantEquipementDTO.getFiabilite());
        composant.setDisponibilite(composantEquipementDTO.getDisponibilite());
        composant.setDureevie(composantEquipementDTO.getDureevie());
        composant.setDateFonction(composantEquipementDTO.getDatefonction());
        composant.setTbf(composantEquipementDTO.getTbf());
        composant.setMarque(composantEquipementDTO.getMarque());
        composant.setSn(composantEquipementDTO.getSn());

        // Récupérer l'équipement associé
        Equipement equipement = equipementRepository.findById(composantEquipementDTO.getEquipementId())
                .orElse(null);

        if (equipement != null) {
            // Ajouter le composant à l'équipement
            composant.setEquipement(equipement);
            composantRepository.save(composant);  // Sauvegarder le composant

            // Ajouter le composant à la liste de composants de l'équipement
            if (equipement.getComposants() != null) {
                equipement.getComposants().add(composant);
            } else {
                equipement.setComposants(Collections.singletonList(composant));
            }

            // Recalculer et mettre à jour le MTBF de l'équipement
            equipement.mettreAJourMtbf(composant.getTbf()); // La valeur du nouveau composant
            equipement.mettreAJourFiabilite(composant.getTbf());
            equipement.mettreAJourDisponibilite(composant.getTbf());

            // Sauvegarder l'équipement avec le MTBF mis à jour
            equipementRepository.save(equipement);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Si l'équipement n'existe pas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }











}
