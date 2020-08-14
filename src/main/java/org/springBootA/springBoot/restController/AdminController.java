package org.springBootA.springBoot.restController;

import org.springBootA.springBoot.dto.UserDto;
import org.springBootA.springBoot.model.User;
import org.springBootA.springBoot.service.RoleService;
import org.springBootA.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping(value = "user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/userByName/ForSecurity/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        User user = userService.findByEmail(username);
        if (user != null) {
            return new ResponseEntity<>(UserDto.createUserDtoFromUser(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDto>> FindAll() {
        List<User> users = userService.findAll();
        List<UserDto> result = new ArrayList<>();
        if (users != null) {
            for (User value : users
            ) {
                result.add(UserDto.createUserDtoFromUser(value));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(HttpEntity<User> httpEntity) {
        User userForSave = UserDto.generateUserForUpdate(Objects.requireNonNull(httpEntity.getBody()), roleService);
        User getUser = userService.saveUser(userForSave);
        return new ResponseEntity<>(UserDto.deleteUserFromRoleForJson(getUser), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity updateUser(HttpEntity<User> httpEntity) {
        User userForUpdate = UserDto.generateUserForUpdate(Objects.requireNonNull(httpEntity.getBody()), roleService);
        userService.saveUser(userForUpdate);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findUserByID(@PathVariable(name = "id") Long id) {
        User result = userService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
