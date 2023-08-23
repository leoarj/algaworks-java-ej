package com.github.leoarj.algaworks.course.ej.strings.validador.email;

public class ValidadorEmailRegex {

    private static final String EMAIL_REGEX = "[\\w.-]+@[a-z\\d.-]+\\.[a-z]{2,3}";

    public static boolean validar(String email) {

        /*
        * REGEX: Regular Expressions
        *
        * Sequência de caracteres que representa um padrão de busca.
        * Pode ser utilizado amplamente para buscas complexas, validações e manipulação de texto.
        *
        * A classe Pattern do pacote java.util.regex e outras classes do mesmo pacote
        * fornecem suporte para se trabalhar com Regex.
        *
        * Ref.: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html
        */

        /*
        * Com o código abaixo conseguimos substituir toda a lógica implementada anteriormente.
        * Obs.: String.matches chama java.util.regex.Pattern internamente.
        */

        // [\\w.-]+@[a-z\\d.-]+\\.[a-z]{2,3}
        return email.matches(EMAIL_REGEX);
    }
}