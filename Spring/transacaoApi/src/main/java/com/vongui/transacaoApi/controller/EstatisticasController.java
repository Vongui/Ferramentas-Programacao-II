package com.vongui.transacaoApi.controller;

import com.vongui.transacaoApi.business.services.EstatisticaService;
import com.vongui.transacaoApi.controller.dtos.EstatisticaResponseDTO;
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
    public ResponseEntity<EstatisticaResponseDTO> buscarEstatisticaPorId(
            @RequestParam(value = "intervalo", required = false, defaultValue = "60") Integer intervalo) {
        return ResponseEntity.ok(estatisticaService.calcularEstatistica(intervalo));
    }
}
