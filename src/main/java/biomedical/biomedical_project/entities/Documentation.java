package biomedical.biomedical_project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Entity
@Getter
@Setter
public class Documentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private String format;
    private String nom;
    private String filePath;

    @Lob
    private byte[] fileData;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Equipement equipement;

    @ManyToOne
    private Fournisseur fournisseur;

    // Getters et Setters
    public String getFilePath() {
        return filePath; // This method returns the file path
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath; // This method sets the file path
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

}

