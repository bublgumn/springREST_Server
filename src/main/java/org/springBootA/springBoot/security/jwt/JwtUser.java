package org.springBootA.springBoot.security.jwt;

import org.springBootA.springBoot.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {

    private final Long id;

    private final String firstname;

    private final String lastname;

    private final Long age;

    private final String email;

    private final String password;

    private final Collection<? extends GrantedAuthority> role;


    public JwtUser(Long id,
                   String firstname,
                   String lastname,
                   Long age,
                   String email,
                   String password,
                   Collection<? extends GrantedAuthority> role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Collection<? extends GrantedAuthority> getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}
