package com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures;

import com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures.interfaces.ServiceDog;

public class Dobermann extends Dog implements ServiceDog, Comparable<Dobermann> {
    public Dobermann(String name) {
        super(name);
    }

    @Override
    public void serve() {
        System.out.printf("The service Dog %s is serving!%n", getName());
    }

    @Override
    public int compareTo(Dobermann o) {
        return getName().compareTo(o.getName());
    }
}
