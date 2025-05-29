package com.prova.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Table(name = "pacotes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pacotes {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "data_viagem")
    private Date dataViagem;

    @Column(name = "data_retorno")
    private Date dataRetorno;

    @Column(name = "qte_diarias")
    private int qteDiarias;

    @Column(name = "quant_pessoas")
    private int quantPessoas;

    @Column(name = "valor_diaria")
    private Double valorDiaria;

    @Column(name = "valor_total")
    private Double valorTotal;

    /*
    0 -> não pago
    1 -> pago
    2 -> cancelado
    * */
    @Column(name = "status")
    private int status = 0;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}
