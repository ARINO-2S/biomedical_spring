package biomedical.biomedical_project.services;

import biomedical.biomedical_project.dto.CompteDTO;
import biomedical.biomedical_project.entities.Compte;
import biomedical.biomedical_project.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public boolean authenticate(CompteDTO compteDTO) {
//        return compteRepository.findByUsername(compteDTO.getUsername())
//                .map(compte -> compte.getPassword().equals(compteDTO.getPassword()))
//                .orElse(false);
//    }
//





    public void saveCompte(Compte compte) {
        compteRepository.save(compte);
    }


    public void saveCompte(CompteDTO compteDTO) {
        // Vérifier si le username est déjà pris
        if (compteRepository.existsByUsername(compteDTO.getUsername())) {
            throw new IllegalArgumentException("Ce nom d'utilisateur est déjà utilisé.");
        }

        // Convertir le DTO en entité et enregistrer
        Compte compte = new Compte();
        compte.setNom(compteDTO.getNom());
        compte.setPrenom(compteDTO.getPrenom());
        compte.setCin(compteDTO.getCin());
        compte.setEmail(compteDTO.getEmail());
        compte.setTelephone(compteDTO.getTelephone());
        compte.setUsername(compteDTO.getUsername());
        // Ne pas encoder le mot de passe, le stocker tel quel
        compte.setPassword(compteDTO.getPassword());

        compteRepository.save(compte);
    }


    public void deleteCompte(String username) {
        Compte compte = compteRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Le compte avec le username '" + username + "' est introuvable."));
        compteRepository.delete(compte);
    }


    public void addUser(CompteDTO compteDTO) {
        Compte compte = new Compte();
        compte.setNom(compteDTO.getNom());
        compte.setPrenom(compteDTO.getPrenom());
        compte.setCin(compteDTO.getCin());
        compte.setEmail(compteDTO.getEmail());
        compte.setTelephone(compteDTO.getTelephone());
        compte.setUsername(compteDTO.getUsername());
//        compte.setPassword(passwordEncoder.encode(compteDTO.getPassword()));
        compteRepository.save(compte);
    }
}
