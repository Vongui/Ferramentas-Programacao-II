package com.vongui.exercicioFB.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "descricao", nullable = false)
    @NotBlank(message = "Descricao é Obrigatório")
    private String descricao;

    @Column(name = "preco", precision = 10, scale = 2, nullable = false)
    @NotNull(message = "Preço é Obrigatório")
    private BigDecimal preco;

    @Column(name = "quantidade", nullable = false)
    @Positive(message = "O quantidade deve ser positiva!!!")
    @NotNull(message = "Quantidade é Obrigatória")
    private Integer quantidade;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.INATIVO;
}
