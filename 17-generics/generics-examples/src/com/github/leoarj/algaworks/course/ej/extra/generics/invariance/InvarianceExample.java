package com.github.leoarj.algaworks.course.ej.extra.generics.invariance;

import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Dog;

import java.util.ArrayList;
import java.util.List;

public class InvarianceExample {

    public static List<Dog> getListOfDogs() {
        /*
         * A atribuição abaixo não compila porque causa da invariancia (invariance),
         * e em Java coleções são invariantes.
         * Dog é um subtipo de Animal que é um subtipo de Object,
         * porém List<Dog> não é um subtipo de List<Object>.
         *
         * Aqui, como List foi utilizada com Type Parameter = Object,
         * somente outra lista exatamente de object será aceita na atribuição.
         */

        List<Object> animals = null;
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Dog"));
        //animals = dogs; Não permitido

        return  dogs;
    }

    /*
    * Retorna uma lista para e somente para o tipo especificado.
    */
    public static <T> List<T> getListOfDefinedType() {
        return new ArrayList<>();
    }
}
