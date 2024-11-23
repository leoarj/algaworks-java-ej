package com.algaworks.csv;

import com.algaworks.crm.entidade.Cliente;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Refletion API (java.lang.reflect).
 *
 * Permite inspecionar e manipular membros e comportamentos de classes,
 * onde podemos "enxergar" a cada parte da estrutura da classe acessíveis como objetos.
 *
 * Muito utilizada em bibliotecas e frameworks para deixar as operações dinâmicas.
 *
 * .class = retorna instância do tipo Class, que tem a estrutura da classe chamada,
 * como propriedades e métodos.
 */
public class GeradorCSV {

    public static <T> void imprimir(Class<T> clazz, List<T> objetos) {
        // Obtém um array das propriedades declaradas na estrutura da classe.
        // Adicionado suporte para generics.
        Field[] propriedades = clazz.getDeclaredFields();

        // Obtém o nome de cada propriedade (Field) e une em uma String, separda por delimitador
        System.out.println(Arrays.stream(propriedades)
                // Verifica se anotação (@Campo) está presente
                .filter(p -> p.isAnnotationPresent(Campo.class))
                .map(Field::getName)
                .collect(Collectors.joining(";")));

        objetos.forEach(GeradorCSV::imprimir);
    }

    /**
     * Realiza a leitura dinâmicamente dos valores de uma instância do objeto passado.
     * Precisa ser acesso aos Fieds da estrutura (da instância passada),
     * e uma lista de String para adicionar os valores como String e juntá-los com um delimitador.
     */
    private static void imprimir(Object objeto) {
        // Obtém um array das propriedades declaradas na estrutura da classe,
        // porém de determinada instância (acesso a estado de um objeto).
        Field[] propriedades = objeto.getClass().getDeclaredFields();
        List<String> valores = new ArrayList<>();

        try {
            for (Field propriedade : propriedades) {
                // Verifica se anotação (@Campo) está presente
                if (propriedade.isAnnotationPresent(Campo.class)) {
                    // Recuperar instância de anotação presente no campo
                    Campo anotacaoCampo = propriedade.getAnnotation(Campo.class);

                    // Tornar o campo acessível para ler seu valor (visibilidade em tempo de execução).
                    propriedade.setAccessible(true);
                    Object resultado = propriedade.get(objeto);
                    String resultadoString = resultado == null ? "" : resultado.toString();
                    // Verifica valor de propriedade definida na anotação
                    valores.add(anotacaoCampo.maiusculo() ? resultadoString.toUpperCase() : resultadoString);
                }
            }

            System.out.println(String.join(";", valores));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}