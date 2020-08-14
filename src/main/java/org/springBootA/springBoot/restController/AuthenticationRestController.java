package org.springBootA.springBoot.restController;


import org.springBootA.springBoot.dto.AuthenticationRequestDto;
import org.springBootA.springBoot.model.User;
import org.springBootA.springBoot.security.jwt.JwtTokenProvider;
import org.springBootA.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class AuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity login(@RequestBody AuthenticationRequestDto ard) {
        try {
            String username = ard.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, ard.getPassword()));
            User user = userService.findByEmail(username);
            System.out.println(user.getFirstname());
            System.out.println(user.getPassword());
            if (user == null) {
                return null;
            }
            String token = jwtTokenProvider.createToken(username, user.getRole());
            AuthenticationRequestDto result = new AuthenticationRequestDto(username, token);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return null;
        }
    }

}
