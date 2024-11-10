package biomedical.biomedical_project.repositories;

import biomedical.biomedical_project.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
