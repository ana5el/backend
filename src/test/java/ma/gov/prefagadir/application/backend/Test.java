package ma.gov.prefagadir.application.backend;

import ma.gov.prefagadir.application.backend.models.Privilege;
import ma.gov.prefagadir.application.backend.models.Profile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
/*
        Set<Privilege>  privileges = new HashSet<>();
        privileges.add(new Privilege("p1","p1","p1"));
        privileges.add(new Privilege("p2","p2","p2"));
        privileges.add(new Privilege("p3","p3","p3"));
        privileges.add(new Privilege("p4","p4","p4"));

        Profile p = new Profile("profile1","profile1","profile1");
        p.setPrivileges(privileges);

        System.out.println(p);
        p.getPrivileges().removeIf(privilege -> privilege.getName().equals("p2"));
        System.out.println(p);
        p.getPrivileges().add(new Privilege("p5","p5","p5"));
        System.out.println(p);
*/
        //privileges.forEach(privilege -> System.out.println(privilege.toString()));

        List<String> names = Arrays.asList("Peter","Sam","Greg","Ryan");
        names.stream()
                .filter(name -> !name.equals("Sam"))
                .forEach(System.out::println);

    }
}
