package br.edu.ifsp.pep.bcc.controle.demo.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Table(name = "item_ordem_servico")
@IdClass(ItemOrdemServicoId.class)
public class ItemOrdemServico implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "numero", referencedColumnName = "numero")
    private OrdemServico ordemServico;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "nr-item")
    private Integer numeroItem;

    @Column(name = "servico")
    @NotBlank(message = "Servico não pode estar vazio!!")
    @Size(min = 2, max = 100)
    @NotNull
    private String servico;

    @PositiveOrZero
    @NotNull
    private Double valor;

}
