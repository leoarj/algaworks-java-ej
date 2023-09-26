package com.github.leoarj.algaworks.course.ej.functional.lambdas.methodReference;

import com.github.leoarj.algaworks.course.ej.functional.lambdas.methodReference.model.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Lambdas são funções anônimas que cumprem um contrato de uma interface funcional
 * e podem ser passadas como argumento para composição de funções/execução de algoritmos
 * de forma reaproveitável.
 *
 * Mais informações no arquivo na raíz do projeto: functional-lambdas-expressions-method-reference.md
 */

public class LambdasExample {

    public static void main(String[] args) {
        testListInstancesBySupplier(() -> Product.getProductList());
        testInstancesBySupplier(getSupplierOfProduct());
        testInstancesByConstructorLambda(str -> new Product(str));
    }

    private static void testListInstancesBySupplier(Supplier<List<Product>> supplier) {
        // Obtendo lista de instâncias de Product via Supplier:
        testFunctionalInterfaces(supplier.get());
    }

    private static void testInstancesBySupplier(Supplier<Product> supplier) {
        // Obtendo instâncias de Product via Supplier:
        List<Product> productsViaSupplier = new ArrayList<>();
        productsViaSupplier.add(supplier.get());
        productsViaSupplier.add(supplier.get());
        productsViaSupplier.add(supplier.get());
        productsViaSupplier.add(supplier.get());

        testFunctionalInterfaces(productsViaSupplier);
    }

    private static void testInstancesByConstructorLambda(Function<String, Product> function) {
        // Utilizando constructor reference:
        List<Product> productsViaConstructorLambda = new ArrayList<>();
        productsViaConstructorLambda.add(function.apply("Produto 1"));
        productsViaConstructorLambda.add(function.apply("Produto 2"));
        productsViaConstructorLambda.add(function.apply("Produto 3"));

        testFunctionalInterfaces(productsViaConstructorLambda);
    }

    private static void testFunctionalInterfaces(List<Product> products) {
        testLambdasPredicate(products);
        //testLambdasPredicate(products, new BigDecimal("1500"));
        testLambdasFunction(products);
        testLambdasConsumer(products);
    }

    /*
    * Pedicate = Condição/teste aplicado em um objeto retornando um valor booleano.
    */
    public static void testLambdasPredicate(List<Product> products) {
        /*
        * O método removeIf() de Collection aplica a remoção de elementos de uma coleção
        * conforme uma função de predicato, onde é aplicado um teste em dado objeto,
        * retornando um valor booleano conforme lógica estabelecida.
        */

        // Podemos declarar uma variável do tipo da interface funcional a ser consumida e atribuir uma expressão:
        //Predicate<Product> productPredicate = product -> product.isOpenBox();
        //products.removeIf(productPredicate);

        // A mesma declaração poderia ficar da seguinte forma, diretamentee sem ter que declarar uma variável:
        products.removeIf(product -> product.isOpenBox());
    }

    /*
     * Encapsula uma lógica de comparação.
     * Nesse caso o método é semelhante a um BiPredicate,
     * porém não pode ser usado diretamente no método removeIf() porque o mesmo aceita apenas um Pedicate.
     * Nesse caso, essa função é utilizada dentro de uma função de transformação, para se obter um Predicate.
     */
    private static boolean isLessThanValue(Product product, BigDecimal filterValue) {
        return product.getValue().compareTo(filterValue) <= 0;
    }

    // Não recomendado
    private static boolean isLessThanValue1500(Product product) {
        var filterValue = new BigDecimal("1500");
        return isLessThanValue(product, filterValue);
    }

    /*
     * Forma mais funcional, que permite uma composição.
     * Este método representa uma Function que recebe um BigDecimal e retorna um Predicate<Product>,
     * encapsulando a lógica de comparação.
     * */
    private static Predicate<Product> getPredicateLessThanValue(BigDecimal filterValue) {
        //return product -> LambdasExample.isLessThanValue1500(product); // Forma mais fixa, não adaptável.
        return product -> LambdasExample.isLessThanValue(product, filterValue); // Forma adaptável com lógica encapsulada.
    }

    public static void testLambdasPredicateProductValue(List<Product> products, BigDecimal filterValue) {
        // Poderíamos também remover por uma regra do valor (Remover produtos maiores que U$1500):
        //Predicate<Product> productPredicateValue = product -> LambdasExample.isLessThanValue1500(product);
        //products.removeIf(productPredicateValue);
        // Ou:
        //products.removeIf(product -> LambdasExample.isLessThanValue1500(product));

        /*
         * Forma mais funcional:
         * Obtém uma lógica de predicato a partir de uma função de transformação, permitindo composição de funções.
         *
         * Dessa forma a comparação do valor fica dinâmica, repassando o argumento "filterValue" para a lógica encapsulada.
         */
        Function<BigDecimal, Predicate<Product>> functionGetPredicate = value -> LambdasExample.getPredicateLessThanValue(filterValue);
        products.removeIf(functionGetPredicate.apply(filterValue)); // Ao chamar apply() obtém o predicate.
    }

    /*
    * Function = Recebimento de um valor e transformação/obtenção de/para outro.
    */
    public static void testLambdasFunction(List<Product> products) {
        /*
         * O método sort() de List aplica a ordenação de elementos de uma coleção
         * conforme uma função de transformação/extração a partir do valor original.
         * No exemplo, está recebendo uma instância de Product e retornando um BigDecimal.
         *
         * É necessário passar um Comparator que consume uma Function.
         */

        //Function<Product, BigDecimal> productFunction = product -> product.getValue();
        //products.sort(Comparator.comparing(productFunction));
        // Ou:
        products.sort(Comparator.comparing(product -> product.getValue()));
    }

    /*
    * Consumer = Recebimento de um valor e processamento do mesmo,sem retorno (void).
    */
    public static void testLambdasConsumer(List<Product> products) {
        /*
         * O método forEach() de Collection aplica o processamento de elementos de uma coleção
         * conforme uma função de consumo, sem retornar algum valor.
         */

        //Consumer<Product> productConsumer = product -> System.out.println(product);
        //products.forEach(productConsumer);
        // Ou:
        //products.forEach(product -> System.out.println(product));

        // Ou:
        products.forEach(product -> {
            product.makeOpenBox();
            System.out.println(product);
        });
    }

    /*
    * Supplier = Fornece/supre um retorno, encapsulando detalhes de implementação para quem for consumir a função.
    */
    public static Supplier<Product> getSupplierOfProduct() {
        //Supplier<Product> productSupplier = () -> new Product(description, value, isOpenBox);
        //return productSupplier;

        // ou
        //return () -> new Product(description, value, isOpenBox);
        return () -> Product.getRandomProduct();

        /*
        * Obs.: Aqui não está sendo criada uma instânica de Product nesse momento,
        * mas sim declarando a lógica do que deve acontecer quando for chamado o método get() da função.
        *
        * Em resumo, a criação de uma nova instância de Product se dará quando o método get()
        * for chamado em alguma referência da interface Supplier.
        */
    }
}