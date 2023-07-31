package com.github.leoarj.algaworks.course.ej.extra.generics.covariance;

import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Animal;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Dobermann;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Dog;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.interfaces.ServiceDog;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class CovarianceExample {

    public static List<? extends Animal> getListOfAnimalUpperbounded() {
        /*
         * Tipo upper bounded (limite superior) e co-variancia.
         *
         * Apenas objetos da classe Animal e suas subclasses são aceitos.
         *
         * Nesse caso, uma List<Dog> pode ser atribuída a uma List<? extends Animal>,
         * dessa forma o conceito de co-variancia (covariance) foi aplicado.
         * Dog é uma subclasse de Animal.
         */

        List<? extends Animal> animals3 = null;
        List<Dog> dogs3 = new ArrayList<>();
        dogs3.add(new Dobermann("Beautiful Dobermann")); // Para testar consumo de objetos
        //List<Object> dogs3Object = new ArrayList<>();
        animals3 = dogs3;
        //animals3 = dogs3Object; // Não permitido (Somente o tipo e seus subtipos, para que a variancia seja validada)

        return animals3;
        //return getListOfDogUpperbounded(); // também pode ser retornado
    }

    public static List<? extends Dog> getListOfDogUpperbounded() {
        // Object -> Creature -> Animal -> Dog -> Dobermann (extends), permitida a atribuição.
        List<? extends Dog> animals3Dogs = null;
        List<Dobermann> dogs3Dobermanns = new ArrayList<>();
        dogs3Dobermanns.add(new Dobermann("Other beautiful Doberman"));
        animals3Dogs = dogs3Dobermanns;

        return animals3Dogs;
    }
}
