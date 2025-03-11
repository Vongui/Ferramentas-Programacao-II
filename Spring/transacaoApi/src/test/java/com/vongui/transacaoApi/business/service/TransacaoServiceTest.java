package com.vongui.transacaoApi.business.service;

import com.vongui.transacaoApi.business.services.TransacaoService;
import com.vongui.transacaoApi.controller.dtos.EstatisticaResponseDTO;
import com.vongui.transacaoApi.controller.dtos.TransacaoRequestDTO;
import com.vongui.transacaoApi.infrastructure.exceptions.UnprocessableEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    EstatisticaResponseDTO estatisticas;

    @BeforeEach
    void setUp(){
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatisticas = new EstatisticaResponseDTO(1l, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void deveAdicionarTransacaoComSucesso(){

        transacaoService.adicionaTransacao(transacao);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoesPorIntervalo(5000);

        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void deveLancarExcecaoCasoValorSejaNegativo(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionaTransacao(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals("Valor não pode ser menor que 0", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoCasoDataOuHoraMaiorQueAtual(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionaTransacao(new TransacaoRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));

        assertEquals("Data e hora maiores que a data e hora atuais", exception.getMessage());
    }

    @Test
    void deveLimparTransacaoComSucesso(){

        transacaoService.deletaTransacao();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoesPorIntervalo(5000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveBuscarTransacaoDentroDoIntervalo(){

        TransacaoRequestDTO dto = new TransacaoRequestDTO(10.00, OffsetDateTime.now().minusHours(1));

        transacaoService.adicionaTransacao(transacao);
        transacaoService.adicionaTransacao(dto);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoesPorIntervalo(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));
    }




}
