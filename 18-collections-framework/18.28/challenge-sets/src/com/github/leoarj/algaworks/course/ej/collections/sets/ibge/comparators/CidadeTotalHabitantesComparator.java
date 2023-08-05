package com.github.leoarj.algaworks.course.ej.collections.sets.ibge.comparators;

import com.github.leoarj.algaworks.course.ej.collections.sets.ibge.Cidade;

import java.util.Comparator;

public class CidadeTotalHabitantesComparator implements Comparator<Cidade> {
    @Override
    public int compare(Cidade o1, Cidade o2) {
        return Integer.compare(o1.getTotalHabitantes(), o2.getTotalHabitantes());
    }
}
