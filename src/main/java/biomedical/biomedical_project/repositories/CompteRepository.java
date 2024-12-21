package biomedical.biomedical_project.repositories;

import biomedical.biomedical_project.entities.Composant;
import biomedical.biomedical_project.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {

    Optional<Compte> findByUsername(String username);
    boolean existsByUsername(String username);


}
