package com.github.leoarj.algaworks.course.ej.exceptions;

public class ContaCorrenteInativaException extends ContaCorrenteException {
    public ContaCorrenteInativaException(String message) {
        super(message);
    }

    public ContaCorrenteInativaException(String message, Throwable cause) {
        super(message, cause);
    }
}
