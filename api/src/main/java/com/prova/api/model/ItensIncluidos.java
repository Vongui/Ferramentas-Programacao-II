package com.prova.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itens_incluidos")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@IdClass(ItensId.class)
public class ItensIncluidos {

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "pacotes_id")
    private Pacotes idPacote;

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "adicionais_id")
    private Adicionais idAdicionais;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "valor")
    private Double valor;

}
