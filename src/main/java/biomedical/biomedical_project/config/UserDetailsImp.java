package biomedical.biomedical_project.config;

import biomedical.biomedical_project.entities.Compte;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImp implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public UserDetailsImp(Compte compte){
        this.username = compte.getUsername();
        this.password = compte.getPassword();
        this.authorities= Arrays.stream(compte.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
