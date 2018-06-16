package com.danielbohry.bolaodacopa.services;

public enum PontosEnum {

    RESULTADO(5),
    VENCEDOR(3),
    NENHUM(0);

    private Integer pontos;

    public Integer getPontos() {
        return pontos;
    }

    PontosEnum(Integer pontos) {
        this.pontos = pontos;
    }
}
