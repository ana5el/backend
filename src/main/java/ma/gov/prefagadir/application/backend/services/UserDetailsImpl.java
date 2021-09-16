package ma.gov.prefagadir.application.backend.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ma.gov.prefagadir.application.backend.models.AgentAutorite;
import ma.gov.prefagadir.application.backend.models.Privilege;
import ma.gov.prefagadir.application.backend.models.User;
import ma.gov.prefagadir.application.backend.utils.GeneralUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String secret;

    private AgentAutorite agentAutorite;

    private boolean active;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password, String secret, Boolean active, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.secret = secret;
        this.active = active;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user){
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getProfile().getName()));
        /*for(Privilege privilege : user.getProfile().getPrivileges()){
            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
        }
        */
        authorities.addAll(GeneralUtils.buildSimpleGrantedAuthorities(user.getProfile().getPrivileges()));
        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(), user.getSecret(), user.isActive(), authorities);
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    public String getSecret() {
        return secret;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public AgentAutorite getAgentAutorite() {
        return agentAutorite;
    }
}
