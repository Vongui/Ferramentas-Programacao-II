package com.prova.api.controller.dto;

import java.util.Date;

public record PacotesDTO(int idCliente, Date dataViagem, Date dataRetorno, int quantPessoas, Double valorDiaria, int qteDiarias) {
}
