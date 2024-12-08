package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.entities.Composant;
import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Fournisseur;
import biomedical.biomedical_project.entities.Intervention;
import biomedical.biomedical_project.services.ComposantService;
import biomedical.biomedical_project.services.EquipementService;
import biomedical.biomedical_project.services.FournisseurService;
import biomedical.biomedical_project.services.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class IdController {

    @Autowired
    private FournisseurService fournisseurService;

    @Autowired
    private EquipementService equipementService;


    @Autowired
    private InterventionService interventionService;

    @Autowired
    private ComposantService composantService;

    @GetMapping("/equipments-list")
    public List<Equipement> getEquipments() {
        return equipementService.getAllEquipements();
    }

    @GetMapping("/fournisseurs-list")
    public List<Fournisseur> getSuppliers() {
        return fournisseurService.getAllFournisseurs();
    }

    @GetMapping("/interventions-list")
    public List<Intervention> getInterventions() {
        return interventionService.getAllInterventions();
    }

//    @GetMapping("/composants-list")
//    public List<Composant> getComposants() {
//        return composantService.getAllComposants();
//    }


}
