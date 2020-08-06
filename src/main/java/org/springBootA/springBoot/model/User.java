package org.springBootA.springBoot.model;

import org.springBootA.springBoot.service.RoleService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    private Long age;

    private String email;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private List<Role> role = new ArrayList<>();

    public User() {
    }

    public User(String firstname, String lastname, Long age, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
    }

    public User(String firstname, String lastname, Long age, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String firstname, String lastname, Long age, String email, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
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

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public void deleteUserFromRoleForJson() {
        for (Role value : getRole()
        ) {
            value.setClient(null);
        }
    }

    public User generateUserForUpdate(User body, RoleService roleService) {
        List<Role> listForUpdateUser = new ArrayList<>();
        for (Role role : body.role
        ) {
            listForUpdateUser.add(roleService.findByName(role.getName()));
        }
        body.setRole(listForUpdateUser);
        return body;
    }

    @Override
    public String toString() {
        String result = "{id: " + id + "} " + System.lineSeparator() +
                "{firstname: " + firstname + "}" + System.lineSeparator() +
                "{lastname: " + lastname + "}" + System.lineSeparator() +
                "{age: " + age + "}" + System.lineSeparator() +
                "{email: " + email + "}" + System.lineSeparator() +
                "{password: " + password + "}" + System.lineSeparator();
        for (Role value : this.role
        ) {
            result = result + "{role: " + value.getName() + "}" + System.lineSeparator();
        }
        return result;
    }
}
