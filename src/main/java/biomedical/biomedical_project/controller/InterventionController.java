package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.dto.InterventionDTO;
import biomedical.biomedical_project.dto.InterventionDtoResponse;
import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Intervention;
import biomedical.biomedical_project.repositories.EquipementRepository;
import biomedical.biomedical_project.repositories.InterventionRepository;
import biomedical.biomedical_project.services.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/intervention")
public class InterventionController {

    @Autowired
    private InterventionService interventionService;
    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private EquipementRepository equipementRepository;

    @PostMapping
    public ResponseEntity<Intervention> addIntervention(@RequestBody Intervention intervention) {
        try {
            Intervention savedIntervention = interventionService.save(intervention);
            return new ResponseEntity<>(savedIntervention, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntervention(@PathVariable Long id) {
        if (interventionRepository.existsById(id)) {
            interventionRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Renvoie 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Renvoie 404 si l'ID n'existe pas
        }
    }
    // Méthode GET pour tester la récupération
    @GetMapping
    public List<InterventionDtoResponse> getInterevention() {
        List<Intervention> interventionList =  interventionRepository.findAll();
        return interventionList.stream().map(InterventionDtoResponse::new).toList();

    }





    @PostMapping("/addNew")
    public ResponseEntity<Void> addNewIntervention(@RequestBody InterventionDTO interventionDto) {
        Intervention intervention = new Intervention();
        intervention.setNom(interventionDto.getNom());
        intervention.setType(interventionDto.getType());
        intervention.setAction(interventionDto.getAction());
        intervention.setDate(interventionDto.getDate());

        Equipement equipement = equipementRepository.findById(interventionDto.getEquipementId())
                .orElse(null);

        intervention.setEquipement(equipement);
        interventionRepository.save(intervention);
        assert equipement != null;
        if(equipement.getInterventions() != null) equipement.getInterventions().add(intervention);
        else equipement.setInterventions(Collections.singletonList(intervention));
        equipementRepository.save(equipement);
        return new ResponseEntity<>(HttpStatus.OK);



    }

}
