package br.edu.ifsp.pep.bcc.controle.demo.controller.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record ClienteDTO(
        @NotBlank(message = "O nome não pode ser vazio!")
        @Size(min = 3, max = 60, message = "O nome precisar ser entre 3 a 60 caracteres")
        String nome,

        @NotBlank(message = "Email não pode ser vazio")
        @NotNull
        @Email(message = "Email inválido!!")
        @Column(name = "email", length = 100 ,nullable = false)
        String email,

        @NotBlank(message = "Telefone não pode ser vazio!!")
        @Pattern(regexp = "(\\(\\d{2}\\)\\s)(\\d{4,5}\\-\\d{4})",
                message = "O padrão para telefone está incorreto, utilize (XX) XXXXX-XXXX")
        @NotNull
        @Column(name = "telefone", length = 16, nullable = false)
        String telefone){
}
