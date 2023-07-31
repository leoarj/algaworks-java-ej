package com.github.leoarj.algaworks.course.ej.extra.generics.util;

import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Creature;

import java.util.List;

public class WildcardUpperboundedUtil {
    // read-only (Posso sempre ler com garantia, não posso escrever com garantia)
    public static void makeCreatureLiveUpperbounded(List<? extends Creature> creatures) {
        // Garantia: Tipo é sempre uma Creature
        for (Creature creature : creatures) {
            creature.live();
        }

        /*
         * Não permitido adicionar (consumer), porque como qualquer tipo que seja Creature
         * ou uma de suas subclasses é aceito, não temos garantia de que o objeto a ser
         * adicionado na lista seja de fato de um subtipo especificado da lista recebida
         * como argumento.
         *
         * Em resumo, a escrita (consumir) não é permitida porque em teoria uma estrutura
         * projetada para um tipo específico poderia receber elementos (instâncias de diferentes subclasses)
         * para os quais não foi projetada.
         *
         * Nesse caso, com o uso de extends, deixamos a estrutura de dados restrita, (mantemos a restrição de tipos).
         */
        //Creature creature = new Creature("Creature");
        //creatures.add(creature);
    }
}
