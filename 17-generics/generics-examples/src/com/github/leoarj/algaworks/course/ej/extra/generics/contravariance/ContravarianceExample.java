package com.github.leoarj.algaworks.course.ej.extra.generics.contravariance;

import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Animal;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Dog;

import java.util.ArrayList;
import java.util.List;

public class ContravarianceExample {

    public static List<? super Animal> getListOfAnimalLowerbounded() {
        /* Tipo lower bounded (limite inferior) e contra-variancia.
         *
         * Apenas objetos da classe Animal e suas superclasses são aceitos.
         *
         * Nesse caso, uma List<Dog> não pode ser atribuída a uma List<? super Animal>,
         * dessa forma o conceito de contra-variancia (contravariance) foi aplicado.
         * Dog não é uma superclasse de Animal.
         */

        List<? super Animal> animals4 = null;
        List <Dog> dogs4 = new ArrayList<>();
        //animals4 = dogs4; // Não permitido (Somente o tipo e seus supertipos, para que a variancia seja validada)
        List<Object> dogs4Object = new ArrayList<>();
        dogs4Object.add("String object");
        dogs4Object.add(new Dog("Dog object"));
        animals4 = dogs4Object; // Permitido. Object na hierarquia é o mais alto supertipo.
        // Obs.: Usar com cuidado para não perder os benefícios de segurança de tipos em tempo de compilação/execução,
        // porque com lowerbounded ampliamos a restrição de tipo para a estrutura de dados trabalhada.

        return animals4;
        // return getListOfDogLowerbounded(); // Não pode ser retornado, porque Dog não é uma superclasse de Animal.
    }

    public static List<? super Dog> getListOfDogLowerbounded() {
        // Dog <- Animal <- Creature <- Object (super), permitida a atribuição.
        List<? super Dog> animals4Dogs = null;
        List<Animal> dogs4Animals = new ArrayList<>();
        animals4Dogs = dogs4Animals;

        return animals4Dogs;
    }
}