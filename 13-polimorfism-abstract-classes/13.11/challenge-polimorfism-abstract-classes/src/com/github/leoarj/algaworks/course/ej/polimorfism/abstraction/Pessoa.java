package com.github.leoarj.algaworks.course.ej.polimorfism.abstraction;

public abstract class Pessoa {

    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract double calcularImpostos();
}
