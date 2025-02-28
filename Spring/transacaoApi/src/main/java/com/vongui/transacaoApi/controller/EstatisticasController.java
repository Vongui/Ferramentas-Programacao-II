package com.vongui.transacaoApi.controller;

import com.vongui.transacaoApi.business.services.EstatisticaService;
import com.vongui.transacaoApi.controller.dtos.EstatisticaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {

    private final EstatisticaService estatisticaService;

    @GetMapping
    @Operation(description = "EndPoint responsável por buscar estisticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatisticas"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    public ResponseEntity<EstatisticaResponseDTO> buscarEstatisticaPorId(
            @RequestParam(value = "intervalo", required = false, defaultValue = "60") Integer intervalo) {
        return ResponseEntity.ok(estatisticaService.calcularEstatistica(intervalo));
    }
}
