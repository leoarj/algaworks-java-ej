package com.github.leoarj.algaworks.course.ej.polimorfism.interfaces.service;

import com.github.leoarj.algaworks.course.ej.polimorfism.interfaces.model.BemSeguravel;

public class ServicoPropostaSeguro {

    public void emitir(BemSeguravel bemSeguravel) {
        System.out.printf("%s%n" +
                "Prêmio: R$%.2f%n",
                bemSeguravel.descrever(), bemSeguravel.calcularValorPremio());
        System.out.println("--------------------");

//        System.out.printf("Teste método default: %s", bemSeguravel.testeDefault());
    }

}
