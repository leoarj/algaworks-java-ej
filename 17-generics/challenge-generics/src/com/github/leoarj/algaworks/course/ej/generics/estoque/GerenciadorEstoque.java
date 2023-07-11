package com.github.leoarj.algaworks.course.ej.generics.estoque;

// Adicionado Type Parameter para que a classe trabalhe apenas com
// um tipo e seus sub-tipos, sem perder acesso aos métodos comuns de Estocavel.
public class GerenciadorEstoque<T extends Estocavel> {

    private int quantidadeTotal;

    public void adicionar(T estocavel) {
        quantidadeTotal += estocavel.getQuantidadeEstoque();
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

}
