package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.entities.Documentation;
import biomedical.biomedical_project.services.DocumentationService;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.Document;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@RestController
@RequestMapping("/api/documentation")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class DocumentationController {

    @Autowired
    private DocumentationService documentationService;



    @PostMapping("/add")
    public ResponseEntity<Object> addDocument(@RequestParam("file") MultipartFile file,
                                              @RequestParam("type") String type,
                                              @RequestParam("format") String format,
                                              @RequestParam("nom") String nom) throws IOException {

        // Vérification du type MIME du fichier
        String mimeType = file.getContentType();

        if (mimeType == null || !mimeType.equals("application/pdf")) {
            // Retourner un message d'erreur structuré en cas d'absence de type MIME valide
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Le fichier doit être un PDF");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // Sauvegarder le document
        Documentation documentation = documentationService.saveDocument(file.getBytes(), type, format, nom);
        return ResponseEntity.ok(documentation);
    }




    // Supprimer un document
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Integer id) {
        documentationService.deleteDocument(id);
        return ResponseEntity.ok("Document supprimé avec succès !");
    }

    // Liste des documents
    @GetMapping("/list")
    public List<Documentation> getAllDocuments() {
        return documentationService.getAllDocuments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getDocumentById(@PathVariable Integer id) {

            Documentation documentation = documentationService.getDocumentById(id);

            if (documentation == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(("Document with ID " + id + " not found").getBytes());
            }

            if (documentation.getFileData() == null || documentation.getFileData().length == 0) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("The file data is empty".getBytes());
            }

            MediaType mediaType;
            try {
                mediaType = MediaType.parseMediaType(documentation.getFormat());
            } catch (InvalidMimeTypeException e) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body(("Invalid MIME type: " + documentation.getFormat()).getBytes());
            }

            String contentDisposition = "attachment; filename=\"" + documentation.getNom() + "\"";

            return ResponseEntity.ok()
                    .contentType(mediaType) // Format du fichier
                    .header("Content-Disposition", contentDisposition) // Téléchargement direct
                    .body(documentation.getFileData());

    }





    @PostMapping("/api/documentation/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Exemple : sauvegarder le fichier dans un dossier
            Path filePath = Paths.get("uploads/" + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            return ResponseEntity.ok("Fichier importé avec succès");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'importation du fichier");
        }
    }


}
