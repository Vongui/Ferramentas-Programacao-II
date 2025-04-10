package br.edu.ifsp.pep.bcc.controle.demo.controller;

import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.OrdemServicoDTO;
import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.OrdemServicoMapper;
import br.edu.ifsp.pep.bcc.controle.demo.controller.exception.NoContent;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.OrdemServico;
import br.edu.ifsp.pep.bcc.controle.demo.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ordem-servico")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;
    private final OrdemServicoMapper ordemServicoMapper;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrdemServicoDTO>> getAll() throws NoContent {
        List<OrdemServico> ordens = ordemServicoService.findAll();
        if (ordens == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ordemServicoMapper.listOrdemServToListOrdemServDto(ordens));
    }

}
