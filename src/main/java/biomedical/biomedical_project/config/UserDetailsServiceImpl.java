package biomedical.biomedical_project.config;

import biomedical.biomedical_project.entities.Compte;
import biomedical.biomedical_project.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CompteRepository compteRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Compte> byUsername = compteRepository.findByUsername(username);
        if(byUsername.isPresent()) {
                return new UserDetailsImp(byUsername.get());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
