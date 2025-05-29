package com.prova.api.controller;


import com.prova.api.controller.dto.ClienteDTO;
import com.prova.api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("cliente")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Cliente Controller", description = "gerenciamento do cliente")
public class ClienteController {
    private final ClienteService clienteService;


    @Operation(summary = "Buscar clientes por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado com o nome fornecido")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteDTO>> buscarPorNome(@RequestParam(value = "nome", defaultValue = "") String nome){
        List<ClienteDTO> clienteDTOS = clienteService.buscarPorNome(nome);
        if (clienteDTOS == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteDTOS);
    }
}
