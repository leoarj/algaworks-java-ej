package com.github.leoarj.algaworks.course.ej.extra.generics.util;

import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Dog;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.interfaces.ServiceDog;

import java.util.List;

/*
* Múltiplas restrições são possíveis (Com Type Parameter),
* onde o tipo tem que atender as restrições (estender e/ou implementar)
* as restrições definidas. */
public class TypeParameterMultipleRestrictionsUtil {
    public static <T extends Dog & ServiceDog & Comparable<T>> void makeDogLiveAndServe(List<T> serviceDogs) {
        /*
        * Com restrições as instâncias podem ser enxergadas de múltiplas formas.
        *
        * Objetos da estrutura podem ser acessados como Dog (superclasse),
        * ou acessados como ServiceDog (interface).
        */

        for (T dog : serviceDogs) {
            dog.live();
            dog.serve(); // Sem a restrição de interface, o método serve() não fica acessível.
            // dog.compareTo() // // Sem a restrição de interface, o método compareTo() não fica acessível.
        }

//        for (ServiceDog serviceDog : serviceDogs) {
//            serviceDog.serve();
//        }
    }
}
