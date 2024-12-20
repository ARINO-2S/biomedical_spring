package biomedical.biomedical_project.repositories;

import biomedical.biomedical_project.entities.Composant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComposantRepository extends JpaRepository<Composant, Integer> {
//    Composant findByNomcomposant(String nomcomposant);
//
//    @Query("SELECT c FROM Composant c WHERE c.fiabilite > :fiabilite")
//    List<Composant> findComposantsWithHighFiabilite(double fiabilite);
@Query("SELECT c FROM Composant c WHERE c.equipement.id = :equipementId")
List<Composant> findByEquipementId(@Param("equipementId") Integer equipementId);



}

