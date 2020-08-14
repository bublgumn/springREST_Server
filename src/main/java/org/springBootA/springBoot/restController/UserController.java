package org.springBootA.springBoot.restController;

import org.springBootA.springBoot.dto.UserDto;
import org.springBootA.springBoot.service.RoleService;
import org.springBootA.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/getThisUser")
    public ResponseEntity<UserDto> getThisUser(HttpEntity<String> userEmail){
        UserDto result = UserDto.createUserDtoFromUser(userService.findByEmail(userEmail.getBody()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
