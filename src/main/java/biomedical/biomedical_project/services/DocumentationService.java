package biomedical.biomedical_project.services;

import biomedical.biomedical_project.entities.Documentation;
import biomedical.biomedical_project.repositories.DocumentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentationService {

    @Autowired
    private DocumentationRepository documentationRepository;

    /**
     * Enregistrer un document dans la base de données.
     *
     * @param file  Les données du fichier sous forme de tableau de bytes.
     * @param type  Le type du document.
     * @param format Le format du document.
     * @param nom   Le nom du document.
     * @return L'objet Documentation sauvegardé.
     */
    public Documentation saveDocument(byte[] file, String type, String format, String nom) {
        Documentation documentation = new Documentation();
        documentation.setType(type);
        documentation.setFormat(format);
        documentation.setNom(nom);
        documentation.setFileData(file); // Les données du fichier en binaire

        // Enregistrer dans la base de données
        return documentationRepository.save(documentation);
    }

    /**
     * Récupérer un document par son ID.
     *
     * @param id L'identifiant du document.
     * @return L'objet Documentation correspondant.
     */
    public Documentation getDocumentById(Integer id) {
        return documentationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document non trouvé pour l'ID : " + id));
    }

    /**
     * Supprimer un document par son ID.
     *
     * @param id L'identifiant du document à supprimer.
     */
    public void deleteDocument(Integer id) {
        if (!documentationRepository.existsById(id)) {
            throw new RuntimeException("Document non trouvé pour l'ID : " + id);
        }
        documentationRepository.deleteById(id);
    }

    /**
     * Récupérer tous les documents.
     *
     * @return Une liste de tous les documents.
     */
    public List<Documentation> getAllDocuments() {
        return documentationRepository.findAll();
    }
}
