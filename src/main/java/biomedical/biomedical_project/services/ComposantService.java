package biomedical.biomedical_project.services;

import biomedical.biomedical_project.entities.Composant;
import biomedical.biomedical_project.repositories.ComposantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ComposantService {

    private static final Logger logger = LoggerFactory.getLogger(ComposantService.class);

    @Autowired
    private ComposantRepository repository;

    // Récupérer tous les composants
    public List<Composant> getAllComposants() {
        List<Composant> composants = repository.findAll();
        logger.info("Récupération de tous les composants: {}", composants);
        return composants;
    }

    // Méthode pour obtenir un composant par son ID
    public Composant getComposantById(Integer id) {
        Optional<Composant> composant = repository.findById(id);
        if (composant.isPresent()) {
            logger.info("Composant trouvé : {}", composant.get());
            return composant.get();
        } else {
            logger.warn("Composant avec l'ID {} non trouvé", id);
            return null; // Vous pouvez aussi lancer une exception si nécessaire
        }
    }

    // Méthode pour créer un composant
    public Composant createComposant(Composant composant) {
        logger.info("Création d'un nouveau composant : {}", composant);
        return repository.save(composant);
    }

    // Méthode pour supprimer un composant par ID
    public void deleteComposant(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            logger.info("Composant avec l'ID {} supprimé", id);
        } else {
            logger.warn("Composant avec l'ID {} non trouvé pour suppression", id);
        }
    }
}
