package biomedical.biomedical_project.repositories;

import biomedical.biomedical_project.entities.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentationRepository extends JpaRepository<Documentation, Integer> {
}
