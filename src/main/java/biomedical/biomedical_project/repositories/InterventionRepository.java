package biomedical.biomedical_project.repositories;

import biomedical.biomedical_project.entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées si nécessaire
}
