package com.github.leoarj.algaworks.course.ej.exceptions;

public class ContaCorrenteDepositarException extends ContaCorrenteException {
    public ContaCorrenteDepositarException(String message) {
        super(message);
    }

    public ContaCorrenteDepositarException(String message, Throwable cause) {
        super(message, cause);
    }
}
