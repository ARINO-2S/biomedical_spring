package biomedical.biomedical_project.services;

import biomedical.biomedical_project.entities.Fournisseur;
import biomedical.biomedical_project.repositories.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    // Méthode pour créer un fournisseur
    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    // Méthode pour supprimer un fournisseur par ID
    public boolean deleteFournisseur(Integer id) {
        if (fournisseurRepository.existsById(id)) {
            fournisseurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Méthode pour récupérer un fournisseur par ID
    public Fournisseur getFournisseurById(Integer id) {
        return fournisseurRepository.findById(id).orElse(null);
    }

    // Récupérer tous les fournisseurs
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();  // Retourne la liste de tous les fournisseurs
    }

}
