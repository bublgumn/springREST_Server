package org.springBootA.springBoot.security.jwt;

import org.springBootA.springBoot.model.Role;
import org.springBootA.springBoot.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getAge(),
                user.getEmail(),
                user.getPassword(),
                getGrantedAuthority(user.getRole()));
    }

    private static List<GrantedAuthority> getGrantedAuthority (List<Role> roles) {
return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
    }
}
