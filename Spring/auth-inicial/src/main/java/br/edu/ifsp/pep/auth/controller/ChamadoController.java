package br.edu.ifsp.pep.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import br.edu.ifsp.pep.auth.entity.Chamado;
import br.edu.ifsp.pep.auth.entity.Prioridade;
import br.edu.ifsp.pep.auth.entity.Status;
import br.edu.ifsp.pep.auth.entity.User;
import br.edu.ifsp.pep.auth.entity.UserRole;
import br.edu.ifsp.pep.auth.entity.dto.ChamadoDTO;
import br.edu.ifsp.pep.auth.entity.dto.StatusDTO;
import br.edu.ifsp.pep.auth.repository.ChamadoRepository;
import br.edu.ifsp.pep.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/chamados")
@RequiredArgsConstructor
public class ChamadoController {

    private final ChamadoRepository chamadoRepository;

    @PostMapping("")
    public ResponseEntity<?> criarChamado (
        @RequestBody ChamadoDTO entityDTO
    ) {
        try {
            if (entityDTO != null) {
                Chamado chamado = new Chamado();

                chamado.setTitulo(entityDTO.titulo());
                chamado.setDescricao(entityDTO.descricao());
                chamado.setPrioridade(entityDTO.prioridade());
                chamado.setDataAbertura(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
                chamado.setStatus(Status.ABERTO);

                chamadoRepository.save(chamado);
                return ResponseEntity.status(HttpStatus.CREATED).body(chamado);
            }    
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Falha ao registrar chamado: " + e.getMessage());
            
        }
        return null;
    }
    
    @GetMapping("")
    public ResponseEntity<List<Chamado>> listarTodos() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user.getRole().equals(UserRole.ADMIN)) {
                return ResponseEntity.ok(chamadoRepository.findAll());
            }
            else if (user.getRole().equals(UserRole.USER)) {
                return ResponseEntity.ok(chamadoRepository.findByPrioridade(Prioridade.BAIXA));
            }
            
        }
        return null;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(
        @PathVariable Long id,
        @RequestBody StatusDTO status
    ) {

        if (!chamadoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Chamado> ch = chamadoRepository.findById(id);

        if (status.status().equals(Status.EM_ANDAMENTO)) {
            if (ch.get().getStatus().equals(Status.ABERTO)) {
                ch.get().setStatus(status.status());
                return ResponseEntity.ok(chamadoRepository.save(opToChamado(ch)));
            }
            else if (ch.get().getStatus().equals(Status.AGUARDANDO_CLIENTE)) {
                ch.get().setStatus(status.status());
                return ResponseEntity.ok(chamadoRepository.save(opToChamado(ch)));
            }
            else if (ch.get().getStatus().equals(Status.REABERTO)) {
                ch.get().setStatus(status.status());
                return ResponseEntity.ok(chamadoRepository.save(opToChamado(ch)));
            }
            else {
                return ResponseEntity.badRequest().body("Não foi possivel realizar a troca do status");
            }
        }
        else if (status.status().equals(Status.AGUARDANDO_CLIENTE)) {
            if (ch.get().getStatus().equals(Status.EM_ANDAMENTO)) {
                ch.get().setStatus(status.status());
                return ResponseEntity.ok(chamadoRepository.save(opToChamado(ch)));
            }
            else {
                return ResponseEntity.badRequest().body("Não foi possivel realizar a troca do status");
            }
        }
        else if (status.status().equals(Status.FINALIZADO)) {
            if (ch.get().getStatus().equals(Status.EM_ANDAMENTO)) {
                ch.get().setStatus(status.status());
                return ResponseEntity.ok(chamadoRepository.save(opToChamado(ch)));
            }
            else if (ch.get().getStatus().equals(Status.REABERTO)) {
                ch.get().setStatus(status.status());
                return ResponseEntity.ok(chamadoRepository.save(opToChamado(ch)));
            }
            else {
                return ResponseEntity.badRequest().body("Não foi possivel realizar a troca do status");
            }
        }
        else if (status.status().equals(Status.REABERTO)) {
            if (ch.get().getStatus().equals(Status.FINALIZADO)) {
                ch.get().setStatus(status.status());
                return ResponseEntity.ok(chamadoRepository.save(opToChamado(ch)));
            }
            else {
                return ResponseEntity.badRequest().body("Não foi possivel realizar a troca do status");
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarChamado(
        @PathVariable Long id
    ) {
        if (!chamadoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        chamadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    public Chamado opToChamado(Optional<Chamado> ch) {
        Chamado c = new Chamado();
        c.setCodigo(ch.get().getCodigo());
        c.setTitulo(ch.get().getTitulo());
        c.setDescricao(ch.get().getDescricao());
        c.setDataAbertura(ch.get().getDataAbertura());
        c.setPrioridade(ch.get().getPrioridade());
        c.setStatus(ch.get().getStatus());
        return c;
    }
    

}
