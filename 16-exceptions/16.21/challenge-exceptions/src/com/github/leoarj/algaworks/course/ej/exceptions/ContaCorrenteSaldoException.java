package com.github.leoarj.algaworks.course.ej.exceptions;

public class ContaCorrenteSaldoException extends ContaCorrenteException {
    public ContaCorrenteSaldoException(String message) {
        super(message);
    }

    public ContaCorrenteSaldoException(String message, Throwable cause) {
        super(message, cause);
    }
}
