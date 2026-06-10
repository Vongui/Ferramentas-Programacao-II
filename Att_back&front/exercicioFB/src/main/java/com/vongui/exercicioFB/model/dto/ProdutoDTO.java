package com.vongui.exercicioFB.model.dto;

import com.vongui.exercicioFB.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ProdutoDTO(
        @NotBlank(message = "Descrição é Obrigatória")
        String descricao,

        @NotNull(message = "Preço é Obrigatório")
        @Positive(message = "O preço deve ser positivo")
        BigDecimal preco,

        @NotNull(message = "Quantidade é Obrigatória")
        @Positive(message = "A quantidade deve ser positiva!!!")
        Integer quantidade,

        Status status
) {}