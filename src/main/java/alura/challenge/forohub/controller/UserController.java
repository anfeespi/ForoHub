package alura.challenge.forohub.controller;

import alura.challenge.forohub.config.security.TokenService;
import alura.challenge.forohub.dto.JWTTokenDTO;
import alura.challenge.forohub.dto.UserDTO;
import alura.challenge.forohub.model.User;
import alura.challenge.forohub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.registerUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserDTO userDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDTO.name(),
                userDTO.password());
        var authUser = authenticationManager.authenticate(authToken);
        var tokenJWT = tokenService.generateToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(tokenJWT));
    }
}
