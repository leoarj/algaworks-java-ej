package com.github.leoarj.algaworks.course.ej.extra.generics.model.bank;

import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.interfaces.Transaction;
import com.github.leoarj.algaworks.course.ej.extra.generics.model.bank.interfaces.TransactionPackager;

public class BankTransactionPackager implements TransactionPackager {

    private final String brand;

    public BankTransactionPackager(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void pack(Transaction transaction) {
        if (transaction.accept(this)) {
            System.out.printf("Transaction ID: %s", transaction.getID());
        }
    }
}
