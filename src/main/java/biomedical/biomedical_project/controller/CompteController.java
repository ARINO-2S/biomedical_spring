package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.dto.CompteDTO;
import biomedical.biomedical_project.dto.LoginDTO;
import biomedical.biomedical_project.entities.Compte;
import biomedical.biomedical_project.repositories.CompteRepository;
import biomedical.biomedical_project.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @Autowired
    private CompteRepository compteRepository;

//    @Autowired
//    private AuthenticationManager authenticationManager;

//    @Autowired
//    private AuthenticationProvider authenticationProvider;
    @PostMapping("New")
    public ResponseEntity<Void> addCompte(@RequestBody CompteDTO compteDTO) {
        compteService.addUser(compteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @PostMapping("authen")
//    public ResponseEntity<Void> authenticate(@RequestBody LoginDTO loginDTO) {
//        System.out.println("icicicicicicicic");
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
//        System.out.println(authenticate);
//        if(authenticate.isAuthenticated()) {
//            System.out.println("Authenticated");
//            SecurityContextHolder.getContext().setAuthentication(authenticate);
//            return ResponseEntity.ok().build();
//        } else  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteCompte(@PathVariable String username) {
        try {
            compteService.deleteCompte(username);
            return ResponseEntity.ok("Compte supprimé avec succès !");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compte introuvable.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du compte.");
        }
    }








    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Compte compte = compteRepository.findByUsername(loginDTO.getUsername())
                .orElse(null);

        if (compte != null && compte.getPassword().equals(loginDTO.getPassword())) {
            return ResponseEntity.ok(Map.of("status", "success", "message", "Connexion réussie !"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("status", "error", "message", "Nom d’utilisateur ou mot de passe incorrect."));
        }
    }




    @PostMapping("/register")
    public String register(@RequestBody CompteDTO compteDTO) {
        Compte compte = new Compte();
        compte.setUsername(compteDTO.getUsername());
        compte.setPassword(compteDTO.getPassword());
        compteService.saveCompte(compte);
        return "Compte enregistré avec succès.";
    }








    @PostMapping("/create")
    public ResponseEntity<String> createCompte(@RequestBody CompteDTO compteDTO) {
        try {
            compteService.saveCompte(compteDTO);
            return ResponseEntity.ok("Compte créé avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la création du compte : " + e.getMessage());
        }
    }

    @GetMapping
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }




}
