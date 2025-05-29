package com.prova.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Table(name = "viajantes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@IdClass(ViajantesId.class)
public class Viajantes {

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "id")
    private Pacotes id;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nasc")
    private Date datanasc;

    public Viajantes(String cpf, String nome, Date datanasc){
        this.cpf = cpf;
        this.nome = nome;
        this.datanasc = datanasc;
    }
}
