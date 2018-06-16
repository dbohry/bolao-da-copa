package com.danielbohry.bolaodacopa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Apostador {

    @Id
    private String id;
    private String nome;
    private List<Palpite> palpites;

    public Apostador() {
    }

    public Apostador(String nome, List<Palpite> palpites) {
        this.nome = nome;
        this.palpites = palpites;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Palpite> getPalpites() {
        return palpites;
    }

    public void setPalpites(List<Palpite> palpites) {
        this.palpites = palpites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Apostador apostador = (Apostador) o;

        return new EqualsBuilder()
                .append(id, apostador.id)
                .append(nome, apostador.nome)
                .append(palpites, apostador.palpites)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(nome)
                .append(palpites)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("nome", nome)
                .append("palpites", palpites)
                .toString();
    }
}
