package biomedical.biomedical_project.repositories;

import biomedical.biomedical_project.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {

    Optional<Compte> findByUsername(String username);
    boolean existsByUsername(String username);

}
