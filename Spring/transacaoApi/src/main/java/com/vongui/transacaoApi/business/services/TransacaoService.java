package com.vongui.transacaoApi.business.services;

import com.vongui.transacaoApi.controller.dtos.TransacaoRequestDTO;
import com.vongui.transacaoApi.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionaTransacao(TransacaoRequestDTO transacaoRequestDTO) {

        log.info("Iniciando a gravação de Transações");
        if (transacaoRequestDTO.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data/Hora maiores que a Data/Hora atual");
            throw new UnprocessableEntity("");
        } else if (transacaoRequestDTO.valor() < 0) {
            log.error("Valor menor que zero");
            throw new UnprocessableEntity("");
        }
        listaTransacoes.add(transacaoRequestDTO);
    }

    public void deletaTransacao() {
        listaTransacoes.clear();
    }

    public List<TransacaoRequestDTO> buscarTransacoesPorDataHora(Integer intervalo) {
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervalo);
        return listaTransacoes.stream().filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();
    }



}
