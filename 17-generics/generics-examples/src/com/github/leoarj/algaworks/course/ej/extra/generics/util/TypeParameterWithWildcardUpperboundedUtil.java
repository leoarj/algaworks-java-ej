package com.github.leoarj.algaworks.course.ej.extra.generics.util;

import java.util.List;

public class TypeParameterWithWildcardUpperboundedUtil {
    /*
     * Utilizando Type Parameters e Wildcards no mesmo método para expandir a reutilização de código.
     * Obs.: Aqui, assim como wildcard unbounded, estamos praticamente tendo acesso apenas a métodos de Object.
     */
    public static <T> void readObjectsFromList(List<? extends T> objects) {
        for (T element : objects) {
            System.out.println(element.toString());
        }
    }
}
