package com.github.leoarj.algaworks.course.ej.extra.generics;

import com.github.leoarj.algaworks.course.ej.extra.generics.contravariance.ContravarianceExample;
import com.github.leoarj.algaworks.course.ej.extra.generics.covariance.CovarianceExample;
import com.github.leoarj.algaworks.course.ej.extra.generics.invariance.InvarianceExample;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.AmericaBankTransactionPackager;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.BankTransaction;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.BankTransactionManager;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.interfaces.Transaction;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.interfaces.TransactionPackager;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Animal;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Creature;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.Dobermann;
import com.github.leoarj.algaworks.course.ej.extra.generics.util.*;
import com.github.leoarj.algaworks.course.ej.extra.generics.variance.raw.VarianceRawExample;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*
        * Acessar as classes abaixo para ver os exemplos de uso dos conceitos.
        *
        * Obs.: Nas classes de exemplos existem métodos com tipos de retornos
        * definidos com wildcards apenas para fins didáticos.
        *
        * Não é recomendado que os métodos tenham retornos com wildcards para não obrigar
        * quem vai consumir o método tenha que ficar preocupado com o tipo de retorno que virá.
        *
        * O ideal é que utilizemos typos com wildcards mais como argumentos de métodos,
        * e caso tenhamos que retornar resultados, o recomendado é se utilizar de Type Parameters (<T>, <R> etc)
        * para definir o retorno sem incertezas e também não perder flexibilidade.
        *
        * Obs.: Esse exercício não faz parte do curso.
        * Foi um estudo extra, com outros materiais para reforçar os tópicos de generics em Java.
        * Mais informações no arquivo na raíz do projeto: README.md
        */

        //InvarianceExample.getListOfDogs();
        var listOfDogs = InvarianceExample.getListOfDogs(); // List<Dog>
        WildcardUnboudedUtil.makeCreatureLiveUnbounded(listOfDogs);
        WildcardUpperboundedUtil.makeCreatureLiveUpperbounded(listOfDogs);
        //WildcardLowerboundedUtil.addCreatureToListLowerbounded(listOfDogs, new Dog("Dog"));

        // InvarianceExample.<T>getListOfDefinedType();
        var animals5 = InvarianceExample.<Creature>getListOfDefinedType(); // List<T>
        WildcardLowerboundedUtil.addCreatureToListLowerbounded(animals5, new Creature("Bird"));
        WildcardLowerboundedUtil.addCreatureToListLowerbounded(animals5, new Animal("Cat"));
        WildcardUnboudedUtil.makeCreatureLiveUnbounded(animals5);
        WildcardUpperboundedUtil.makeCreatureLiveUpperbounded(animals5);

        //VarianceRawExample.getListOfAnything();
        var listOfAnything = VarianceRawExample.getListOfAnything(); // List<?>
        WildcardUnboudedUtil.makeCreatureLiveUnbounded(listOfAnything);
        //WildcardUpperboundedUtil.makeCreatureLiveUpperbounded(listOfAnything);
        //WildcardLowerboundedUtil.addCreatureToListLowerbounded(listOfAnything, new Creature("Creature"));

        //CovarianceExample.getListOfAnimalUpperbounded();
        var listOfAnimalUpperbounded = CovarianceExample.getListOfAnimalUpperbounded(); // List<? extends Animal>
        WildcardUnboudedUtil.makeCreatureLiveUnbounded(listOfAnimalUpperbounded);
        WildcardUpperboundedUtil.makeCreatureLiveUpperbounded(listOfAnimalUpperbounded);
        //WildcardLowerboundedUtil.addCreatureToListLowerbounded(listOfAnimalUpperbounded, new Animal("Animal"));

        //ContravarianceExample.getListOfAnimalLowerbounded();
        var listOfAnimalLowerbounded = ContravarianceExample.getListOfAnimalLowerbounded(); // List<? super Animal>
        WildcardUnboudedUtil.makeCreatureLiveUnbounded(listOfAnimalLowerbounded);
        //WildcardUpperboundedUtil.makeCreatureLiveUpperbounded(listOfAnimalLowerbounded);
        //WildcardLowerboundedUtil.addCreatureToListLowerbounded(listOfAnimalLowerbounded, new Animal("Animal"));

        // Restrições múltiplas de tipo genérico
        List<Dobermann> dobermanns = new ArrayList<>();
        dobermanns.add(new Dobermann("Doberman 1"));
        dobermanns.add(new Dobermann("Doberman 2"));
        TypeParameterMultipleRestrictionsUtil.makeDogLiveAndServe(dobermanns);

        // Outros exemplos (Só pode receber transações que são BankTransaction,
        // e estão marcadas pela interface de marcação java.io.Serializable, por exemplo):
        TransactionPackager packager = new AmericaBankTransactionPackager();
        BankTransactionManager<BankTransaction> manager = new BankTransactionManager<>(packager);
        manager.process(
                List.of(new BankTransaction("1", List.of("AMERICA")),
                        new BankTransaction("2", List.of("EUROPE"))
                )
        );
    }

    //WildcardUnboudedUtil

    //WildcardUpperboundedUtil

    //WildcardLowerboundedUtil

    //TypeParameterWithWildcardUpperboundedUtil

    //TypeParameterWithWildcardLowerboundedUtil

    //TypeParameterMultipleRestrictionsUtil



}
