package com.github.leoarj.algaworks.course.ej.collections.iterables.iterators;

import java.util.*;

/*
* Podemos implementar a interface Iterator para ter compatibilidade com enhanced-for
* e principalmente encapsular a lógica de iteração e retorno de elementos.
*/

public class MegaSenaSorteadorIterator implements Iterator<SortedSet<Integer>> {

    private static final int NUMERO_INICIAL = 1;
    private static final int NUMERO_FINAL = 60;
    private static final int QUANTIDADE_NUMEROS = 6;

    private static final Random RANDOM = new Random();

    private final int quantidadeJogos;
    private int quantidadeJogosCount;
    //private final Random random;

    public MegaSenaSorteadorIterator(int quantidadeJogos) {
        this.quantidadeJogos = quantidadeJogos;
        this.quantidadeJogosCount = 0;
       //this.random = new Random();
    }

    @Override
    public boolean hasNext() {
        return quantidadeJogosCount < quantidadeJogos;
    }

    @Override
    public SortedSet<Integer> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Todos os números já foram sorteados");
        }

        SortedSet<Integer> jogo = new TreeSet<>();

        for (int i = 0; i < QUANTIDADE_NUMEROS; i++) {
            int numeroSorteado;

            do {
                numeroSorteado = RANDOM.nextInt(NUMERO_INICIAL, NUMERO_FINAL);
            } while (!jogo.add(numeroSorteado));
        }

        quantidadeJogosCount++;

        return jogo;
    }
}
