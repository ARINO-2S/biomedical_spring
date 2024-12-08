package biomedical.biomedical_project.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
@ToString
public class Composant {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Integer id;
        private String nomcomposant;
        private double fiabilite;
        private double disponibilite;
        private double dureevie;
        private double tbf;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private Date datefonction;

        @ManyToOne
        private Fournisseur Composant;
        @ManyToOne
        private Equipement equipement;
        @ManyToOne
        private GestionDeStock gestionDeStock;
        @ManyToOne
        private Intervention intervention;




        public Date getDateFonction() {
                return datefonction;
        }

        public void setDateFonction(Date datefonction) {
                this.datefonction = datefonction;
        }

}
