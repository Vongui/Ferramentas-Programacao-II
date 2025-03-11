package br.edu.ifsp.pep.bcc.estoque.controller.dto;

import br.edu.ifsp.pep.bcc.estoque.model.entities.Client;


public record ClientDTO(String nome, String email, String telefone) {

    public Client tranformaParaObjeto(){
        return new Client(nome, email, telefone);
    }

}
