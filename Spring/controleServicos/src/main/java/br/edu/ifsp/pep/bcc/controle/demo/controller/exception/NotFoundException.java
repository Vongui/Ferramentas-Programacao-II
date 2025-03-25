package br.edu.ifsp.pep.bcc.controle.demo.controller.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}
