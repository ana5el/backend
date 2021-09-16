package ma.gov.prefagadir.application.backend.utils;

import ma.gov.prefagadir.application.backend.models.Privilege;
import ma.gov.prefagadir.application.backend.services.UserDetailsImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralUtils {

    public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Privilege> privileges){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Privilege privilege : privileges){
            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
        }
        return authorities;
    }

    public static List<String> rolesAsListOfSting(UserDetailsImpl userDetails){
        return userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
    }
}
