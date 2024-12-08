package biomedical.biomedical_project.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Service
public class ComposantDTO {
    private String nomcomposant;
    private double fiabilite;
    private double disponibilite;
    private double dureevie;
    private Date datefonction;
    private double tbf;






}
