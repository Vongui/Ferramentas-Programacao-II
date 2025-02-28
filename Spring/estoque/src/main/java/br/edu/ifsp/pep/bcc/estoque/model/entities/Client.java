package br.edu.ifsp.pep.bcc.estoque.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter // cria todos os gets da entity
@Setter // cria todos os setters da entity
@NoArgsConstructor // cria um construtor vazio
@AllArgsConstructor // cria um construtor com todos os atributos
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // cria o metodo equals e hashCode

@Table(name = "cliente")
public class Client {

    @Id
    @Column(name = "codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // incluindo o atributo no equals and hashCode
    private int codigo;

    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @Column(name = "email", length = 30 ,nullable = false)
    private String email;

    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;

    @Column(name = "ativo")
    private int ativo;
}
