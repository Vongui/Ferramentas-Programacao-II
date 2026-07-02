package br.edu.ifsp.pep.auth.entity;

public enum Prioridade {
    BAIXA("baixa"),
    MEDIA("media"),
    ALTA("alta");

    private String prioridade;

    Prioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getprioridade() {
        return prioridade;
    }

}
