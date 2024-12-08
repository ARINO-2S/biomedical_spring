package biomedical.biomedical_project.services;

import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Fournisseur;
import biomedical.biomedical_project.entities.Intervention;
import biomedical.biomedical_project.repositories.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionService {

    @Autowired
    private InterventionRepository interventionRepository;

    // Méthode pour sauvegarder une intervention
    public Intervention save(Intervention intervention) {
        return interventionRepository.save(intervention);
    }

    public List<Intervention> getAllInterventions() {
        System.out.println(interventionRepository.findAll());
        return interventionRepository.findAll();
    }

}
