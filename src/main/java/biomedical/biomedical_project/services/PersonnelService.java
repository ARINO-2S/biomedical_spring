package biomedical.biomedical_project.services;

import biomedical.biomedical_project.entities.Fournisseur;
import biomedical.biomedical_project.entities.Personnel;
import biomedical.biomedical_project.repositories.FournisseurRepository;
import biomedical.biomedical_project.repositories.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    // Méthode pour créer un personnel
    public Personnel createPersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    // Méthode pour supprimer un personnel par ID
    public boolean deletePersonnel(Integer id) {
        if (personnelRepository.existsById(id)) {
            personnelRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Méthode pour récupérer un Personnel par ID
    public Personnel getPersonnelById(Integer id) {
        return personnelRepository.findById(id).orElse(null);
    }

    // Récupérer tous les personnels
    public List<Personnel> getAllPersonnels() {
        return personnelRepository.findAll();  // Retourne la liste de tous les personnels
    }

}
