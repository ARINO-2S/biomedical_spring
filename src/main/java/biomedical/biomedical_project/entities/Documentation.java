package biomedical.biomedical_project.entities;

import jakarta.persistence.*;

@Entity
public class Documentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private String format;
    private String nom;
    @Lob
    private byte[] fileData;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Equipement equipement;
    @ManyToOne
    private Fournisseur fournisseur;

}
