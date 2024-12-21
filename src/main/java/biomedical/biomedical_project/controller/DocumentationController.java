package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.dto.DocumentationDTO;
import biomedical.biomedical_project.dto.DocumentationEquipementDTO;
import biomedical.biomedical_project.dto.ImageDTO;
import biomedical.biomedical_project.dto.InterventionDtoResponse;
import biomedical.biomedical_project.entities.Documentation;
import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Intervention;
import biomedical.biomedical_project.repositories.DocumentationRepository;
import biomedical.biomedical_project.repositories.EquipementRepository;
import biomedical.biomedical_project.services.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RestController
@RequestMapping("/api/documentation")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class DocumentationController {

    @Autowired
    private DocumentationService documentationService;

    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private DocumentationRepository documentationRepository;



    @PostMapping
    public ResponseEntity<Documentation> addDocumentation(
            @RequestParam String nom,
            @RequestParam String type,
            @RequestParam("fileData") MultipartFile fileData) throws IOException {

        Documentation documentation = documentationService.addDocumentation(nom, type, fileData);
        return ResponseEntity.ok(documentation);
    }



    // API pour récupérer une documentation par ID
    @GetMapping("/{id}")
    public ResponseEntity<DocumentationEquipementDTO> getDocumentationById(@PathVariable Integer id) {
        Optional<Documentation> documentation = documentationService.getDocumentationById(id);
        return documentation.map(doc -> ResponseEntity.ok(new DocumentationEquipementDTO(doc)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Integer id) {
        Optional<Documentation> documentation = documentationService.getDocumentationById(id);
        if (documentation.isPresent() && documentation.get().getFileData() != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg") // Ajuster selon le format réel de l'image
                    .body(documentation.get().getFileData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }






    // API pour récupérer toutes les documentations
    @GetMapping
    public List<DocumentationEquipementDTO> getAllDocumentations() {
        List<Documentation> documentationList =  documentationRepository.findAll();
        return documentationList.stream().map(DocumentationEquipementDTO::new).toList();
    }




    @PostMapping("/addNew")
    public ResponseEntity<Map<String, String>> addNewDocumentation(@RequestBody DocumentationDTO documentationDto) {
        Documentation documentation = new Documentation();

        // Remplissage des champs de la documentation à partir du DTO
        documentation.setNom(documentationDto.getNom());
        documentation.setType(documentationDto.getType());
        documentation.setFileData(documentationDto.getFileData());

        // Recherche de l'équipement associé
        Equipement equipement = equipementRepository.findById(documentationDto.getEquipementId()).orElse(null);

        if (equipement == null) {
            // L'équipement n'a pas été trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Équipement non trouvé"));
        }

        // Sauvegarde de la documentation
        try {
            documentation.setEquipement(equipement);
            documentationRepository.save(documentation);

            // Mise à jour de l'équipement avec la documentation associée
            if (equipement.getDocumentations() != null) {
                equipement.getDocumentations().add(documentation);
            } else {
                equipement.setDocumentations(Collections.singletonList(documentation));
            }

            equipementRepository.save(equipement);

            // Réponse avec message de succès
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("message", "Documentation ajoutée avec succès"));

        } catch (Exception e) {
            // Gestion des erreurs lors de l'ajout
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erreur lors de l'ajout de la documentation", "error", e.getMessage()));
        }
    }




    // API pour supprimer une documentation par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteDocumentationById(@PathVariable Integer id) {
        try {
            // Vérification si la documentation existe
            Optional<Documentation> documentation = documentationRepository.findById(id);

            if (documentation.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Documentation non trouvée"));
            }

            // Suppression de la documentation
            documentationRepository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("message", "Documentation supprimée avec succès"));

        } catch (Exception e) {
            // Gestion des erreurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erreur lors de la suppression de la documentation", "error", e.getMessage()));
        }
    }








}
