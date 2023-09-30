package com.github.leoarj.algaworks.course.ej.functional.lambdas.methodReference;

import com.github.leoarj.algaworks.course.ej.functional.lambdas.methodReference.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/*
 * Method reference representa uma referência a um método de uma classe,
 * seja ele da instância ou estático.
 *
 * Caso a assinatura do método (argumentos ou retorno dependendo do caso)
 * obedeça à especificação de uma interface funcional, então ele pode ser passado como argumento.
 *
 * Demais explicações sobre Predicate, Function, Consumer e Supplier estão na classe "LambdasExample".
 *
 * Mais informações no arquivo na raíz do projeto: README.md
 */

public class MethodReferenceExample {

    public static void main(String[] args) {
        testListInstancesBySupplier(Product::getProductList);
        testInstancesBySupplier(getSupplierOfProduct());
        testInstancesByConstructorReference(getConstructorReferenceOfProduct());
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

    private static void testInstancesByConstructorReference(Function<String, Product> function) {
        // Utilizando constructor reference:
        List<Product> productsViaConstructorReference = new ArrayList<>();
        productsViaConstructorReference.add(function.apply("Produto 1"));
        productsViaConstructorReference.add(function.apply("Produto 2"));
        productsViaConstructorReference.add(function.apply("Produto 3"));

        testFunctionalInterfaces(productsViaConstructorReference);
    }

    private static void testFunctionalInterfaces(List<Product> products) {
        //testMethodReferencePredicate(products);
        testMethodReferencePredicateProductValue(products, new BigDecimal("1500"));
        testMethodReferenceFunction(products);
        testMethodReferenceConsumer(products);
    }

    /*
     * Podemos declarar uma variável do tipo da interface funcional a ser consumida e atribuir uma method reference.
     * No caso, o compilador já infere que o método de uma instância de Product que deverá ser chamado.
     * O compilador também já infere que o argumento a ser passado é uma instância de Product.
     *
     * É levada em consideração a tipagem da coleção ou variável de interface funcional para realizar a inferência.
     * Caso a referência de método seja de um tipo diferente da coleção/função, então algumas regras devem ser observadas:
     *
     * - É possível referenciar um método estático de outra classe no mesmo contexto estático chamando por "NomeClasse.metodoEstatico".
     *
     * - É possível referenciar um método de instância de outra classe desde que seja criada uma instância
     * dessa classe atribuindo a uma variável e em seguida referenciando o método a partir do nome da "variável::metodo".
     *
     */

    public static void testMethodReferencePredicate(List<Product> products) {
        //Predicate<Product> productPredicate = Product::isOpenBox;
        //products.removeIf(productPredicate);

        products.removeIf(Product::isOpenBox);
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
        //return MethodReferenceExample::isLessThanValue1500; // Forma mais fixa, não adaptável.
        return product -> MethodReferenceExample.isLessThanValue(product, filterValue); // Forma adaptável com lógica encapsulada.
    }

    public static void testMethodReferencePredicateProductValue(List<Product> products, BigDecimal filterValue) {
        // Poderíamos também remover por uma regra do valor (Remover produtos maiores que U$1500):
        //Predicate<Product> productPredicateValue = MethodReferenceExample::isLessThanValue1500;
        //products.removeIf(productPredicateValue);
        // Ou:
        //products.removeIf(MethodReferenceExample::isLessThanValue1500);

        /*
        * Forma mais funcional:
        * Obtém uma lógica de predicato a partir de uma função de transformação,
        * unindo method references e composição de funções.
        *
        * Dessa forma a comparação do valor fica dinâmica, repassando o argumento "filterValue" para a lógica encapsulada.
        */
        Function<BigDecimal, Predicate<Product>> functionGetPredicate = MethodReferenceExample::getPredicateLessThanValue;
        products.removeIf(functionGetPredicate.apply(filterValue)); // Ao chamar apply() obtém o predicate.
    }

    public static void testMethodReferenceFunction(List<Product> products) {
        //Function<Product, BigDecimal> productFunction = Product::getValue;
        //products.sort(Comparator.comparing(productFunction));
        // Ou:
        products.sort(Comparator.comparing(Product::getValue));
    }

    public static void testMethodReferenceConsumer(List<Product> products) {
        // Necessário declarar uma variável devido à composição de consumers:
        Consumer<Product> productConsumer = Product::makeOpenBox;
        productConsumer = productConsumer.andThen(System.out::println);

        products.forEach(productConsumer);
    }

    public static Supplier<Product> getSupplierOfProduct() {
        // Referência a um método que retorna uma instância de Product.
        return Product::getRandomProduct;
    }

    public static Function<String, Product> getConstructorReferenceOfProduct() {
        // Referência a um construtor de Product;
        return Product::new;
    }
}