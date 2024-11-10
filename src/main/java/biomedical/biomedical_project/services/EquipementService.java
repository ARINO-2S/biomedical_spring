package biomedical.biomedical_project.services;

import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.repositories.EquipementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementService {
    @Autowired
    private EquipementRepository repository;

    public Equipement updateCategorie(Integer id,String categorie) {
        Optional<Equipement> byId = repository.findById(id);
        if (byId.isPresent()) {
            Equipement equipement = byId.get();
            equipement.setCategorie(categorie);
            Equipement updatedEquipement = repository.save(equipement);
            System.out.println(updatedEquipement);
            return updatedEquipement;
        }
        else throw new RuntimeException("Equipement not found");

    }
    public List<Equipement> getAllEquipements() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }
}
