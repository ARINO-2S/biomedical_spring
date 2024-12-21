package biomedical.biomedical_project.repositories;

import biomedical.biomedical_project.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockRepository extends JpaRepository<Stock, Integer> {

}