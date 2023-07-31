package com.github.leoarj.algaworks.course.ej.extra.generics.util;

import java.util.List;

public class TypeParameterWithWildcardLowerboundedUtil {
    /*
     * Utilizando Type Parameters e Wildcards no mesmo método para expandir a reutilização de código.
     */
    public static <T> void addObjectsToList(List<? super T> objects, T element) {
        objects.add(element);
    }
}
