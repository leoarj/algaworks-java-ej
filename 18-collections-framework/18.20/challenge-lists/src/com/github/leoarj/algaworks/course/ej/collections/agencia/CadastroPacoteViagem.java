package com.github.leoarj.algaworks.course.ej.collections.agencia;

import com.github.leoarj.algaworks.course.ej.collections.agencia.exceptions.PacoteViagemExistenteException;
import com.github.leoarj.algaworks.course.ej.collections.agencia.exceptions.PacoteViagemNaoEncontradoException;

import java.util.*;

public class CadastroPacoteViagem {

    // TODO declarar lista de pacotes de viagem aqui
    private List<PacoteViagem> pacotesViagem;

    public CadastroPacoteViagem() {
        this.pacotesViagem = new ArrayList<>();
    }

    public void adicionar(String descricao, double precoDiaria) throws PacoteViagemExistenteException {
        // DONE adicionar pacote na lista, validando antes se
        //  já existe usando contains (lançar exceção)
        PacoteViagem pacoteViagem = new PacoteViagem(descricao, precoDiaria);

        if (pacotesViagem.contains(pacoteViagem)) {
            throw new PacoteViagemExistenteException(
                    String.format("Pacote de viagem %s já existe na lista", "\"" + pacoteViagem.getDescricao() + "\"")
            );
        }

        pacotesViagem.add(pacoteViagem);
    }

    public List<PacoteViagem> obterTodos() {
        // DONE retornar lista de pacotes
        return pacotesViagem;
    }

    public void ordenar() {
        // DONE ordenar pacotes pela ordem natural
        System.out.println("Ordenando lista por ordem natural...\n");
        Collections.sort(pacotesViagem);
    }

    public void ordenarPorPrecoDecrescente() {
        // DONE ordenar pacotes pelo preço (decrescente)
        System.out.println("Ordenando lista por ordem decrescente/reversa...\n");
        Collections.sort(pacotesViagem, Comparator.reverseOrder());
    }

    public void removerPorDescricao(String descricao) throws PacoteViagemNaoEncontradoException {
        // DONE iterar nos pacotes com Iterator e remover aqueles com descrição informada,
        //  lançando exceção se nenhum pacote foi removido

        Iterator<PacoteViagem> iterator = pacotesViagem.iterator();
        int totalRemovidos = 0;

        while (iterator.hasNext()) {
            if (iterator.next().getDescricao().equals(descricao)) {
                iterator.remove();
                totalRemovidos++;
            }
        }

        if (totalRemovidos == 0) {
            throw new PacoteViagemNaoEncontradoException(
                    String.format("Pacote de viagem com descrição %s não encontrado", "\"" + descricao + "\"")
            );
        }
    }

    public PacoteViagem buscarPorDescricao(String descricao) throws PacoteViagemNaoEncontradoException {
        // DONE iterar pacotes com enhanced for, localizar e retornar
        //  pacote com descrição informada (ou lançar exceção se não encontrar)

        PacoteViagem pacoteViagemEncontrado = null;

        for (PacoteViagem pacoteViagem : pacotesViagem) {
            if (pacoteViagem.getDescricao().equals(descricao)) {
                pacoteViagemEncontrado = pacoteViagem;
                break;
            }
        }

        if (pacoteViagemEncontrado == null) {
            throw new PacoteViagemNaoEncontradoException(
                    String.format("Pacote de viagem com descrição %s não encontrado", "\"" + descricao + "\"")
            );
        }

        return pacoteViagemEncontrado;
    }
}
