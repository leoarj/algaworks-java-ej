package com.github.leoarj.algaworks.course.ej.extra.generics.model.bank;

import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.interfaces.Transaction;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.interfaces.TransactionPackager;

import java.io.Serializable;
import java.util.List;

public class BankTransaction implements Transaction, Serializable {

    private final String id;
    private final List<String> brandsAcepted;

    public BankTransaction(String id, List<String> brandsAccepted) {
        this.id = id;
        this.brandsAcepted = brandsAccepted;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public boolean accept(TransactionPackager packager) {
        return this.brandsAcepted.contains(packager.getBrand());
    }
}
