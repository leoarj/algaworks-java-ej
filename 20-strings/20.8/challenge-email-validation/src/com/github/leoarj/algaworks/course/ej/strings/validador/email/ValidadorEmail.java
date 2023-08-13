package com.github.leoarj.algaworks.course.ej.strings.validador.email;

public class ValidadorEmail {

    public static boolean validar(String email) {
        return validarNomeUsuario(email) && validarDominio(email) && validarSuxfixoDominio(email);
    }

    private static boolean validarNomeUsuario(String email) {
        boolean isValido = false;
        String nomeUsuario = obterNomeUsuario(email);

        isValido = validarSeNaoVazio(nomeUsuario)
                && validarSeIniciaComCaractereOuDigito(nomeUsuario)
                && validarCaracteres(nomeUsuario, false, false, '_', '-', '.');

        return isValido;
    }

    private static boolean validarDominio(String email) {
        boolean isValido = false;
        String dominio = obterDominio(email);

        isValido = validarSeNaoVazio(dominio)
                && validarSeIniciaComCaractereOuDigito(dominio)
                && validarCaracteres(dominio, false, true, '-', '.');

        return isValido;
    }

    private static boolean validarSuxfixoDominio(String email) {
        boolean isValido = false;
        String sufixoDominio = obterSufixoDominio(email);

        isValido = validarSeNaoVazio(sufixoDominio)
                && validarQuantidadeCaracteres(sufixoDominio, 2, 3)
                && validarSeIniciaComCaractereOuDigito(sufixoDominio)
                && validarCaracteres(sufixoDominio, true, true);

        return isValido;
    }

    private static boolean validarSeNaoVazio(String email) {
        return !email.isBlank();
    }

    private static boolean validarSeCaracterLetraOuDigito(char caractere) {
        return (Character.isLetter(caractere)) || (Character.isDigit(caractere));
    }

    private static boolean validarSeCaracterSomenteLetra(char caractere) {
        return (Character.isLetter(caractere));
    }

    private static boolean validarSeCaracterAceito(char caractere, char... caracteresAceitos) {
        boolean isCaracterAceito = false;

        for (char caractereAceito : caracteresAceitos) {
            if (caractere == caractereAceito) {
                isCaracterAceito = true;
                break;
            }
        }

        return isCaracterAceito;
    }

    private static boolean validarSeIniciaComCaractereOuDigito(String email) {
        char caracterInicio = email.charAt(0);
        return Character.isLetter(caracterInicio) || (Character.isDigit(caracterInicio));
    }

    private static boolean validarSeCaractereMinusculo(char caractere) {
        return Character.isLowerCase(caractere);
    }

    private static boolean validarCaracteres(String email, boolean validarSeApenasLetras, boolean validarSeApenasCaracteresMinusculos, char... caracteresAceitos) {
        boolean isCaracteresValidos = true;

        //for (char c : email.toCharArray())
        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);

            if (validarSeApenasLetras && !validarSeCaracterSomenteLetra(c)) {
                isCaracteresValidos = false;
                break;
            }

            if (validarSeApenasCaracteresMinusculos && validarSeCaracterSomenteLetra(c)) {
                if (!validarSeCaractereMinusculo(c)) {
                    isCaracteresValidos = false;
                    break;
                }
            }

            if (!validarSeCaracterLetraOuDigito(c)) {
                if (caracteresAceitos.length > 0) {
                    if (!validarSeCaracterAceito(c, caracteresAceitos)) {
                        isCaracteresValidos = false;
                        break;
                    }
                }
            }
        }

        return isCaracteresValidos;
    }

    private static boolean validarQuantidadeCaracteres(String valor, int quantidadeMinima, int quantidadeMaxima) {
        return (valor.length() >= quantidadeMinima) && (valor.length() <= quantidadeMaxima);
    }

    private static String obterNomeUsuario(String email) {
        int indexArroba = email.indexOf('@');

        return (indexArroba < 0 ? "" : email.substring(0, indexArroba));
    }

    private static String obterDominio(String email) {
        String nomeUsuario = obterNomeUsuario(email);
        int indexInicioDominio = email.indexOf(nomeUsuario) + nomeUsuario.length() + 1;

        if (indexInicioDominio > email.length()) {
            return "";
        }

        String dominio = email.substring(indexInicioDominio);
        int indexUltimoPonto = dominio.lastIndexOf('.');

        return (indexUltimoPonto < 0 ? "" : dominio.substring(0, indexUltimoPonto));
    }

    private static String obterSufixoDominio(String email) {
        String dominio = obterDominio(email);
        int indexInicioSufixo = email.indexOf(dominio) + dominio.length() + 1;

        return (indexInicioSufixo > email.length() ? "" : email.substring(indexInicioSufixo));
    }
}