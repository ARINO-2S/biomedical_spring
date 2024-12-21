package biomedical.biomedical_project.repositories;

import biomedical.biomedical_project.entities.Documentation;
import biomedical.biomedical_project.entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentationRepository extends JpaRepository<Documentation, Integer> {
    @Query("SELECT c FROM Documentation c WHERE c.equipement.id = :equipementId")
    List<Documentation> findByEquipementId(@Param("equipementId") Integer equipementId);
}
