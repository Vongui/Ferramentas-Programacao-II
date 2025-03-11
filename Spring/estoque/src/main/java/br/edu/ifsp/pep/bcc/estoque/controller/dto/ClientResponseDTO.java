package br.edu.ifsp.pep.bcc.estoque.controller.dto;

import br.edu.ifsp.pep.bcc.estoque.model.entities.Client;

public record ClientResponseDTO(String nome, String email, String telefone) {
    public static ClientResponseDTO transformaEmDto(Client client){
        return new ClientResponseDTO(client.getNome(), client.getEmail(), client.getTelefone());
    }
}
