package com.github.leoarj.algaworks.course.ej.extra.generics.variance.raw;

import java.util.ArrayList;
import java.util.List;

public class VarianceRawExample {

    public static List<?> getListOfAnything() {
        /*
         * Qualquer tipo aqui é aceito, porque o operador ? permite maior flexibilidade.
         * Estamos praticamente trabalhando com Object.
         * A diferença aqui é que por causa do wildcard a invariancia não se aplica (entende-se como "qualquer tipo"),
         * permitindo que uma coleção bruta (raw) receba outra coleção com tipagem definida.
         */

        List<?> animals2 = null;
        List<String> dogs2 = new ArrayList<>();
        dogs2.add("Dog String");
        animals2 = dogs2;

        return animals2;
    }
}
