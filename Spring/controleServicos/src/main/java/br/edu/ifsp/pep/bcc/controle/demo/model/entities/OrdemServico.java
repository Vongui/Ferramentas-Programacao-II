package br.edu.ifsp.pep.bcc.controle.demo.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
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
    private Date dataAbertura;

    @Column(name = "data_fechamento")
    private Date dataFechamento;

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

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordemServico", orphanRemoval = true)
//    List<ItemOrdemServico> servicos;

    public OrdemServico(String formaPagamento, Cliente cliente){
        this.dataAbertura = new Date();
        this.formaPagamento = formaPagamento;
        this.status = 1;
        this.cliente = cliente;
    }


}
