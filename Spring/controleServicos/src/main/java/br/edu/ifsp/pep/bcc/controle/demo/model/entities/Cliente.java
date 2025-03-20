package br.edu.ifsp.pep.bcc.controle.demo.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int codigo;

    @NotBlank(message = "O nome não pode ser vazio!")
    @Size(min = 3, max = 60, message = "O nome precisar ser entre 3 a 60 caracteres")
    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @NotBlank(message = "Email não pode ser vazio")
    @NotNull
    @Email(message = "Email inválido!!")
    @Column(name = "email", length = 100 ,nullable = false)
    private String email;

    @NotBlank(message = "Telefone não pode ser vazio!!")
    @Pattern(regexp = "(\\(\\d{2}\\)\\s)(\\d{4,5}\\-\\d{4})",
            message = "O padrão para telefone está incorreto, utilize (XX) XXXXX-XXXX")
    @NotNull
    @Column(name = "telefone", length = 16, nullable = false)
    private String telefone;

    @Positive
    @Column(name = "ativo")
    @NotNull
    private int ativo;

    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.ativo = 1;
    }

}
