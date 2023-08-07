package com.github.leoarj.algaworks.course.ej.collections.iterables;

import com.github.leoarj.algaworks.course.ej.collections.iterables.iterators.MegaSenaSorteadorIterator;

import java.util.Iterator;
import java.util.SortedSet;

/*
* Para se utilizar do recurso de enhanced-for devemos implementar a interface Iterable,
* que por usa vez deve retornar um objeto Iterator que contém a lógica de interação nos elementos.
*/

public class MegaSenaSorteador implements Iterable<SortedSet<Integer>> {

    private final int quantidadeJogos;

    public MegaSenaSorteador(int quantidadeJogos) {
        this.quantidadeJogos = quantidadeJogos;
    }

    @Override
    public Iterator<SortedSet<Integer>> iterator() {
        return new MegaSenaSorteadorIterator(quantidadeJogos);
    }
}
