package org.springBootA.springBoot.restController;

import org.springBootA.springBoot.model.User;
import org.springBootA.springBoot.service.RoleService;
import org.springBootA.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    /*
     * http://localhost:8081/admin/userByName/ForSecurity/*
     */
    @GetMapping("/userByName/ForSecurity/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByEmail(username);
        if (user != null) {
            user.deleteUserFromRoleForJson();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> FindAll() {
        List<User> result = userService.findAll();
        if (result != null) {
            for (User value : result
            ) {
                value.deleteUserFromRoleForJson();
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(HttpEntity<User> httpEntity) {
        User userForSave = httpEntity.getBody().generateUserForUpdate(httpEntity.getBody(), roleService);
        User getUser = userService.saveUser(userForSave);
        getUser.deleteUserFromRoleForJson();
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity updateUser(HttpEntity<User> httpEntity) {
        User userForUpdate = httpEntity.getBody().generateUserForUpdate(httpEntity.getBody(), roleService);
        userService.saveUser(userForUpdate);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findUserByID(@PathVariable(name = "id") Long id) {
        User result = userService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
