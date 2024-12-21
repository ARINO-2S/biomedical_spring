package biomedical.biomedical_project.services;

import biomedical.biomedical_project.entities.Documentation;
import biomedical.biomedical_project.repositories.DocumentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentationService {

    @Autowired
    private DocumentationRepository documentationRepository;

    // Méthode pour ajouter une nouvelle documentation
    public Documentation addDocumentation(String nom, String type, MultipartFile fileData) throws IOException {
        Documentation doc = new Documentation();
        doc.setNom(nom);
        doc.setType(type);
        doc.setFileData(fileData.getBytes());  // Enregistre l'image sous forme de tableau d'octets (BLOB)
        doc = documentationRepository.save(doc);
        return doc;

    }

    // Méthode pour récupérer une documentation par son ID
    public Optional<Documentation> getDocumentationById(Integer id) {
        return documentationRepository.findById(id);
    }

    // Méthode pour récupérer toutes les documentations
    public List<Documentation> getAllDocumentations() {
        return documentationRepository.findAll();
    }
}
