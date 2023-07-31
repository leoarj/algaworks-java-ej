package com.github.leoarj.algaworks.course.ej.extra.generics.model.bank;

import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.interfaces.Transaction;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.interfaces.TransactionPackager;

import java.io.Serializable;
import java.util.List;

/*
* Teste: Aceitar apenas transações de banco.
*/

public class BankTransactionManager<T extends BankTransaction & Serializable> {

    private final TransactionPackager packager;

    public BankTransactionManager(TransactionPackager packager) {
        this.packager = packager;
    }

    public void process(List<T> transactions) {
        for (T transaction : transactions) {
            packager.pack(transaction);
        }
    }
}
