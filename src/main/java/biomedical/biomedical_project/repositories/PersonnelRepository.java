package biomedical.biomedical_project.repositories;



import biomedical.biomedical_project.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {

}