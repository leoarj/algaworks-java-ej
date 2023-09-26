package com.github.leoarj.algaworks.course.ej.functional.lambdas.methodReference;

/*
* Referência a construtores são possíveis, desde que os argumentos do construtor correspondam com os argumentos da função.
*/

import com.github.leoarj.algaworks.course.ej.functional.lambdas.methodReference.model.Product;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ConstructorsReferenceExample {

    public static void main(String[] args) {
        testeConstructorReference();
    }

    public static void testeConstructorReference() {
        // Uma function que recebem um argumento (description) e corresponde ao construtor de Product,
        // com a finalidade de obter uma instância de Product.
        Function<String, Product> functionCreateProduct = Product::new;

        Product product = functionCreateProduct.apply("Produto (constructor reference)");
        System.out.println(product);

        ProductFunctionCreate productFunctionCreate = Product::new; // Compilador já infere qual construtor utilizar

        Product product2 = productFunctionCreate.createNewProduct("Produtor (constructor reference adicional)",
                new BigDecimal("250"), false);
        System.out.println(product2);
    }
}