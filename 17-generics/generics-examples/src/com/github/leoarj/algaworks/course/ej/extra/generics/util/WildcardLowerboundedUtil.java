package com.github.leoarj.algaworks.course.ej.extra.generics.util;

import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Creature;

import java.util.List;

public class WildcardLowerboundedUtil {
    // write-only (Posso sempre escrever com garantia, não posso ler com garantia)
    public static void addCreatureToListLowerbounded(List<? super Creature> creatures, Creature element) {
        /*
         * Não permitido recuperar (producer), porque como qualquer tipo que seja Creature
         * ou uma de suas superclasses é aceito como tipo da lista, não temos garantia
         * de que o tipo da lista seja de fato uma Creature (a não ser que se utilize instanceof ou cast direto),
         * diferentemente do uso de extends.
         */
//        for (Creature creature : creatures) {
//            creature.live();
//        }

        /*
         * Permitido, mas desencorajado, já que perdemos os benefícios de generics,
         * além de introduzir um cast que pode gerar exception em run-time.
         */
//        for (Creature creature : (List<Creature>) creatures) {
//            creature.live();
//        }

        /*
         * Permitido adicionar (consumer) já que Animal está na hierarquia de Creature,
         * sendo assim uma Creature ou algum supertipo (herda a hierarquia).
         *
         * Nesse caso, com uso de super, deixamos a estrutura de dados mais abrangente,
         * já que ela pode receber instâncias de diferentes subclasses (ampliamos a restrição de tipos).
         */
        // Garantia: Tipo é sempre uma Creature e herda sua hierarquia.
        //creatures.add(new Creature("Bird"));
        //creatures.add(new Animal("Cat"));
        //creatures.add(element);

        TypeParameterWithWildcardLowerboundedUtil.addObjectsToList(creatures, element);
    }
}
