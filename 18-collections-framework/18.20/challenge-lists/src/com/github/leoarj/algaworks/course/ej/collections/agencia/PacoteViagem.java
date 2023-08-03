package com.github.leoarj.algaworks.course.ej.collections.agencia;

import java.util.Objects;

public class PacoteViagem implements Comparable<PacoteViagem> {

    private String descricao;
    private double precoPorPessoa;

    public PacoteViagem(String descricao, double precoPorPessoa) {
        Objects.requireNonNull(descricao);

        if (precoPorPessoa < 0) {
            throw new IllegalArgumentException("Preço por pessoa não pode ser negativo");
        }

        this.descricao = descricao;
        this.precoPorPessoa = precoPorPessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoPorPessoa() {
        return precoPorPessoa;
    }

    public void setPrecoPorPessoa(double precoPorPessoa) {
        this.precoPorPessoa = precoPorPessoa;
    }

    @Override
    public String toString() {
        return "com.github.leoarj.algaworks.course.ej.collections.agencia.PacoteViagem{" +
                "descricao='" + descricao + '\'' +
                ", precoPorPessoa=" + precoPorPessoa +
                '}';
    }

    // DONE implementar equals e hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PacoteViagem that = (PacoteViagem) o;

        if (Double.compare(that.precoPorPessoa, precoPorPessoa) != 0) return false;
        return Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = descricao != null ? descricao.hashCode() : 0;
        temp = Double.doubleToLongBits(precoPorPessoa);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    // DONE implementar compareTo

    @Override
    public int compareTo(PacoteViagem o) {
        int result = this.descricao.compareTo(o.descricao);
        if (result == 0) {
            result = Double.compare(this.precoPorPessoa, o.precoPorPessoa);
        }

        return result;
    }
}
