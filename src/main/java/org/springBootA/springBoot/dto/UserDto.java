package org.springBootA.springBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springBootA.springBoot.model.Role;
import org.springBootA.springBoot.model.User;
import org.springBootA.springBoot.service.RoleService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;

    private String firstname;

    private String lastname;

    private Long age;

    private String email;

    private String password;

    private List<Role> role = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(Long id, String firstname, String lastname, Long age, String email, String password, List<Role> role) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public static UserDto createUserDtoFromUser(User user) {
        return new UserDto(
                user.getId(), user.getFirstname(),
                user.getLastname(), user.getAge(),
                user.getEmail(), user.getPassword(),
                UserDto.deleteUserFromRoleForJson(user).getRole()
        );
    }

    public static User deleteUserFromRoleForJson(User user) {
        for (Role value : user.getRole()
        ) {
            value.setClient(null);
        }
        return user;
    }

    public static User generateUserForUpdate(User body, RoleService roleService) {
        List<Role> listForUpdateUser = new ArrayList<>();
        for (Role role : body.getRole()) {
            listForUpdateUser.add(roleService.findByName(role.getName()));
        }
        body.setRole(listForUpdateUser);
        return body;
    }

}
