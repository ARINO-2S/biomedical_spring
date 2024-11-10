package biomedical.biomedical_project.web;

import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.services.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class EquipementApi {
    @Autowired
    private EquipementService equipementService;

    @GetMapping("/equipement/{id}")
    public ResponseEntity<Equipement> updateCategorie(@PathVariable Integer id,
                                                      @RequestParam String categorie) {
        // Mettre à jour l'équipement
        Equipement equipement = equipementService.updateCategorie(id, categorie);

        // Si l'équipement est trouvé, renvoyer la réponse en JSON
        if (equipement != null) {
            return ResponseEntity.ok(equipement);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Renvoyer un statut 404 si l'équipement n'est pas trouvé
        }
    }
    @GetMapping("/equipements")
    public ResponseEntity<List<Equipement>> getAllEquipements() {
        List<Equipement> equipements = equipementService.getAllEquipements();

        if (equipements.isEmpty()) {
            System.out.println("Aucun équipement trouvé");
            // Vous pouvez aussi retourner une réponse HTTP avec un statut 404 ou 204 si la liste est vide
            return ResponseEntity.noContent().build(); // Réponse HTTP 204 No Content
        }

        return ResponseEntity.ok(equipements); // Réponse HTTP 200 OK avec les équipements
    }


}
