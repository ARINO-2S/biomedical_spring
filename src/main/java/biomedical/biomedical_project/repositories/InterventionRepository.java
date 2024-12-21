package biomedical.biomedical_project.repositories;

import biomedical.biomedical_project.entities.Composant;
import biomedical.biomedical_project.entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées si nécessaire
    @Query("SELECT c FROM Intervention c WHERE c.equipement.id = :equipementId")
    List<Intervention> findByEquipementId(@Param("equipementId") Integer equipementId);

}
