package biomedical.biomedical_project.repositories;

import biomedical.biomedical_project.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    List<Fournisseur> findByNom(String nom);
    List<Fournisseur> findByEmail(String email);
    List<Fournisseur> findByEmailContaining(String email);
    List<Fournisseur> findByNomOrEmail(String nom, String email);
}
