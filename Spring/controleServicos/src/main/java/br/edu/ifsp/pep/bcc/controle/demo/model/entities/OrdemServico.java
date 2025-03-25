package br.edu.ifsp.pep.bcc.controle.demo.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Table(name = "ordem_servico")
public class OrdemServico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "numero")
    private int numero;

    @Column(name = "data_abertura")
    @NotNull
    private LocalDate dataAbertura;

    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    @Column(name = "forma_pagamento")
    @NotBlank(message = "Forma de pagamento não pode estar em branco!!")
    @NotNull
    private String formaPagamento;

    @Column(name = "status")
    @NotNull
    private int status;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "codigo")
    @NotNull
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordemServico", orphanRemoval = true)
    List<ItemOrdemServico> servicos = new ArrayList<>();

    public OrdemServico(String formaPagamento, Cliente cliente){
        this.dataAbertura = LocalDate.now();
        this.formaPagamento = formaPagamento;
        this.status = 1;
        this.cliente = cliente;
    }


}
