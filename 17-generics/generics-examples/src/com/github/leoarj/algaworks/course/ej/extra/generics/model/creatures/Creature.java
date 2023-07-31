package com.github.leoarj.algaworks.course.ej.extra.generics.model.creatures;

public class Creature {

    private String name;

    public Creature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void live() {
        System.out.printf("The creature %s is living!%n", getName());
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                '}';
    }
}
