package com.github.leoarj.algaworks.course.ej.exceptions;

public class ContaCorrenteException extends Exception {
    public ContaCorrenteException(String message) {
        super(message);
    }

    public ContaCorrenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
