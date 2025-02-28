package com.vongui.transacaoApi.business.services;

import com.vongui.transacaoApi.controller.dtos.EstatisticaResponseDTO;
import com.vongui.transacaoApi.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticaService {

    private final TransacaoService transacaoService;

    public EstatisticaResponseDTO calcularEstatistica(Integer intervalo) {
        log.info("iniciando busca de estatistica pelo tempo -> {}", intervalo);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoesPorIntervalo(intervalo);

        if(transacoes.isEmpty()) {
            return new EstatisticaResponseDTO(0L, 0.0,0.0 , 0.0, 0.0);

        }

        DoubleSummaryStatistics estatistica = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        log.info("Estatisticas retornadas com sucesso");
        return new EstatisticaResponseDTO(estatistica.getCount(), estatistica.getSum(),
                estatistica.getAverage(), estatistica.getMin(), estatistica.getMax());

    }
}
