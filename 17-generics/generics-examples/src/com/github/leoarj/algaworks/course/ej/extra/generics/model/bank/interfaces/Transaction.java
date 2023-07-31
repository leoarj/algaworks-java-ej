package com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.interfaces;

public interface Transaction {

    String getID();
    boolean accept(TransactionPackager packager);
}
