package br.edu.ifsp.pep.bcc.controle.demo.controller;

import br.edu.ifsp.pep.bcc.controle.demo.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ordem-servico")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    
}
