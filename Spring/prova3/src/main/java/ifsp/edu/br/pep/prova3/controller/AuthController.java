package ifsp.edu.br.pep.prova3.controller;

import ifsp.edu.br.pep.prova3.entity.dto.AuthenticationDTO;
import ifsp.edu.br.pep.prova3.entity.Cliente;
import ifsp.edu.br.pep.prova3.entity.dto.LoginResponseDTO;
import ifsp.edu.br.pep.prova3.entity.dto.RegisterDTO;
import ifsp.edu.br.pep.prova3.repository.ClienteRepository;
import ifsp.edu.br.pep.prova3.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final ClienteRepository clienteRepository;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO novoCliente) {
        if (clienteRepository.findByLogin(novoCliente.login()) != null) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado.");
        }

//        String senhaHash = new BCryptPasswordEncoder().encode(novoCliente.getPassword());
//        novoCliente.setPassword(senhaHash);

        Cliente salvo = new Cliente();
        salvo.setNome(novoCliente.nome());
        salvo.setLogin(novoCliente.login());
        salvo.setPassword(novoCliente.password());
        clienteRepository.save(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {

        Cliente cliente = this.clienteRepository.findByLogin(data.login());

//        if (cliente == null || !new BCryptPasswordEncoder().matches(data.password(), cliente.getPassword())) {
//            return ResponseEntity.status(401).body("Login ou senha inválidos");
//        }

        if (cliente == null) {
            return ResponseEntity.status(401).body("Login ou senha inválidos");
        }
        var token = this.tokenService.generateToken(cliente);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}