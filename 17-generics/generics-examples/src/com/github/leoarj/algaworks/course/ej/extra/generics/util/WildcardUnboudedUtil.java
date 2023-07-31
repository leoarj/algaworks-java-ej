package com.github.leoarj.algaworks.course.ej.extra.generics.util;

import java.util.List;

public class WildcardUnboudedUtil {

    public static void makeCreatureLiveUnbounded(List<?> creatures) {
//        for (Object creature : creatures) {
//            //creature.live(); Sem acesso aos métodos de Creature
//            System.out.println(creature.toString()); Com acesso aos métodos de Object
//        }

        TypeParameterWithWildcardUpperboundedUtil.readObjectsFromList(creatures);
    }
}
