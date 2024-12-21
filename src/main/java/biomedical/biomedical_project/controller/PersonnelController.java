package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.dto.EquipementAllDTO;
import biomedical.biomedical_project.dto.EquipementDtoResponse;
import biomedical.biomedical_project.dto.PersonnelDTO;
import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Fournisseur;
import biomedical.biomedical_project.entities.Personnel;
import biomedical.biomedical_project.repositories.FournisseurRepository;
import biomedical.biomedical_project.repositories.PersonnelRepository;
import biomedical.biomedical_project.services.FournisseurService;
import biomedical.biomedical_project.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnels")
public class PersonnelController {


        @Autowired
        private PersonnelService personnelService;

        @Autowired
        private PersonnelRepository personnelRepository; // Injection du service uniquement

        // Méthode POST pour créer un personnel
        @PostMapping
        public ResponseEntity<Personnel> createPersonnel(@RequestBody Personnel personnel) {
            System.out.println("Données reçues : " + personnel);
            Personnel savedPersonnel = personnelService.createPersonnel(personnel);  // Utilisation du service
            return ResponseEntity.ok(savedPersonnel);
        }



        // Méthode DELETE pour supprimer un personnel par ID
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletePersonnel(@PathVariable Integer id) {
            boolean deleted = personnelService.deletePersonnel(id);  // Utilisation du service
            if (deleted) {
                return ResponseEntity.noContent().build(); // Renvoie 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // Renvoie 404 si l'ID n'existe pas
            }
        }

        // Méthode GET pour récupérer un personnel par ID
        @GetMapping("/{id}")
        public ResponseEntity<PersonnelDTO> getPersonnelById(@PathVariable Integer id) {
            Personnel personnel = personnelService.getPersonnelById(id);  // Utilisation du service

            if (personnel == null) {
                return ResponseEntity.notFound().build();  // Renvoie 404 si le fournisseur n'est pas trouvé
            }

            return ResponseEntity.ok(new PersonnelDTO(personnel));  // Renvoie le fournisseur avec un statut 200 OK
        }

        // Méthode GET pour récupérer tous les personnels

    @GetMapping
    public List<PersonnelDTO> getPersonnels() {
        List<Personnel> personnels = personnelRepository.findAll();
        return personnels.stream().map(PersonnelDTO::new).toList();
    }
}
