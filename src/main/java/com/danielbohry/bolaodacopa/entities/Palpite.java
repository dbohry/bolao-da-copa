package com.danielbohry.bolaodacopa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

public class Palpite {

    @Id
    private String id;

    private String timeA;
    private String timeB;
    private Integer resultadoTimeA;
    private Integer resultadoTimeB;

    public Palpite() {
    }

    public Palpite(String timeA, String timeB, Integer resultadoTimeA, Integer resultadoTimeB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.resultadoTimeA = resultadoTimeA;
        this.resultadoTimeB = resultadoTimeB;
    }

    public String getId() {
        return id;
    }

    public String getTimeA() {
        return timeA;
    }

    public String getTimeB() {
        return timeB;
    }

    public Integer getResultadoTimeA() {
        return resultadoTimeA;
    }

    public Integer getResultadoTimeB() {
        return resultadoTimeB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Palpite partida = (Palpite) o;

        return new EqualsBuilder()
                .append(id, partida.id)
                .append(timeA, partida.timeA)
                .append(timeB, partida.timeB)
                .append(resultadoTimeA, partida.resultadoTimeA)
                .append(resultadoTimeB, partida.resultadoTimeB)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(timeA)
                .append(timeB)
                .append(resultadoTimeA)
                .append(resultadoTimeB)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("timeA", timeA)
                .append("timeB", timeB)
                .append("resultadoTimeA", resultadoTimeA)
                .append("resultadoTimeB", resultadoTimeB)
                .toString();
    }
}
