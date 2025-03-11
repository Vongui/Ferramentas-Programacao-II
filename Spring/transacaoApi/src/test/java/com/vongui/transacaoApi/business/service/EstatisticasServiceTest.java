package com.vongui.transacaoApi.business.service;

import com.vongui.transacaoApi.business.services.EstatisticaService;
import com.vongui.transacaoApi.business.services.TransacaoService;
import com.vongui.transacaoApi.controller.dtos.EstatisticaResponseDTO;
import com.vongui.transacaoApi.controller.dtos.TransacaoRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticasServiceTest {

    @InjectMocks
    EstatisticaService estatisticaService;

    @Mock
    TransacaoService transacaoService;

    TransacaoRequestDTO transacaoRequestDTO;

    EstatisticaResponseDTO estatisticaResponseDTO;

    @BeforeEach
    void setUp() {
        transacaoRequestDTO = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatisticaResponseDTO = new EstatisticaResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void calcularEstatisticasComSucesso(){
        when(transacaoService.buscarTransacoesPorIntervalo(60))
                .thenReturn(Collections.singletonList(transacaoRequestDTO));

        EstatisticaResponseDTO resultado = estatisticaService.calcularEstatistica(60);

        verify(transacaoService, times(1)).buscarTransacoesPorIntervalo(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticaResponseDTO);
    }

    @Test
    void calcularEstatisticasQuandoListaVazia(){

        EstatisticaResponseDTO estasticaEsperado =
                new EstatisticaResponseDTO(0l, 0.0, 0.0, 0.0, 0.0);

        when(transacaoService.buscarTransacoesPorIntervalo(60))
                .thenReturn(Collections.emptyList());

        EstatisticaResponseDTO resultado = estatisticaService.calcularEstatistica(60);

        verify(transacaoService, times(1)).buscarTransacoesPorIntervalo(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estasticaEsperado);
    }


}
