package com.vongui.transacaoApi.controller;

import com.vongui.transacaoApi.business.services.TransacaoService;
import com.vongui.transacaoApi.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO){
        transacaoService.adicionaTransacao(transacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removerTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO){
        transacaoService.deletaTransacao();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
