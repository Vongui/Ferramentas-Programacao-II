package br.edu.ifsp.pep.auth.controller;

import br.edu.ifsp.pep.auth.entity.User;
import br.edu.ifsp.pep.auth.entity.dto.AuthenticationDTO;
import br.edu.ifsp.pep.auth.entity.dto.LoginResponseDTO;
import br.edu.ifsp.pep.auth.entity.dto.RegisterDTO;
import br.edu.ifsp.pep.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.edu.ifsp.pep.auth.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        System.out.println("login");
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = this.tokenService.generateToken((User) auth.getPrincipal());

            System.out.println(token);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (org.springframework.security.core.AuthenticationException e) {

            System.err.println("=== FALHA NA AUTENTICAÇÃO ===");
            e.printStackTrace();

            return ResponseEntity.status(403).body(e.getMessage());
        }

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        System.out.println("Register");
        if (this.userRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), encryptedPassword, data.role());

        System.out.println("Saving user: " + user.getUsername() + ", role: " +
                user.getAuthorities() + ", password: " + user.getPassword());
        this.userRepository.save(user);

        return ResponseEntity.ok(user);
    }

}
