package com.github.leoarj.algaworks.course.ej.strings.validador.email;

public class ValidadorEmail {

    public static boolean validar(String email) {
        return validarNomeUsuario(email) && validarDominio(email) && validarSuxfixoDominio(email);
    }

    private static boolean validarNomeUsuario(String email) {
        String nomeUsuario = obterNomeUsuario(email);
        return validarSeNaoVazio(nomeUsuario)
                && validarSeIniciaComCaractereOuDigito(nomeUsuario)
                && validarCaracteres(nomeUsuario, false, false, '_', '-', '.');
    }

    private static boolean validarDominio(String email) {
        String dominio = obterDominio(email);
        return validarSeNaoVazio(dominio)
                && validarSeIniciaComCaractereOuDigito(dominio)
                && validarCaracteres(dominio, false, true, '-', '.');
    }

    private static boolean validarSuxfixoDominio(String email) {
        String sufixoDominio = obterSufixoDominio(email);
        return validarSeNaoVazio(sufixoDominio)
                && validarQuantidadeCaracteres(sufixoDominio, 2, 3)
                && validarSeIniciaComCaractereOuDigito(sufixoDominio)
                && validarCaracteres(sufixoDominio, true, true);
    }

    private static boolean validarSeNaoVazio(String email) {
        return !email.isBlank();
    }

    private static boolean validarSeCaracterLetraOuDigito(char caractere) {
        return (Character.isLetter(caractere)) || (Character.isDigit(caractere));
    }

    private static boolean validarSeCaracterLetra(char caractere) {
        return (Character.isLetter(caractere));
    }

    private static boolean validarSeCaractereDigito(char caractere) {
        return (Character.isDigit(caractere));
    }

    private static boolean validarSeCaracterAceito(char caractere, char... caracteresAceitos) {
        for (char caractereAceito : caracteresAceitos) {
            if (caractere == caractereAceito) {
                return true;
            }
        }

        return false;
    }

    private static boolean validarSeIniciaComCaractereOuDigito(String email) {
        return validarSeCaracterLetraOuDigito(email.charAt(0));
    }

    private static boolean validarSeCaractereMinusculo(char caractere) {
        return Character.isLowerCase(caractere);
    }

    private static boolean validarCaracteres(String email, boolean validarSeApenasLetras, boolean validarSeApenasCaracteresMinusculos, char... caracteresAceitos) {
//        boolean isCaracteresValidos = true;

        //for (char c : email.toCharArray())
        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);

            if (validarSeApenasLetras && !validarSeCaracterLetra(c)) {
                return false;
            }

            if (validarSeApenasCaracteresMinusculos && validarSeCaracterLetra(c)) {
                if (!validarSeCaractereMinusculo(c)) {
                    return false;
                }
            }

            if (!validarSeCaracterLetraOuDigito(c) && !validarSeCaracterAceito(c, caracteresAceitos)) {
                return false;
            }

            // Outra opção:
//            isCaracteresValidos = (validarSeCaracterLetra(c) && (!validarSeApenasCaracteresMinusculos || validarSeCaractereMinusculo(c)))
//                    || (!validarSeApenasLetras && (validarSeCaractereDigito(c) || validarSeCaracterAceito(c, caracteresAceitos)));
//
//            if (!isCaracteresValidos) {
//                break;
//            }
        }

//        return isCaracteresValidos;
        return true;
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