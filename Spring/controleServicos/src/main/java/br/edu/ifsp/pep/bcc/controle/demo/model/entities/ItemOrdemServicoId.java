package br.edu.ifsp.pep.bcc.controle.demo.model.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class ItemOrdemServicoId implements Serializable {

    private int ordemServico;
    private int numeroItem;
}
