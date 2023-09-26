package com.github.leoarj.algaworks.course.ej.functional.lambdas.methodReference;

import com.github.leoarj.algaworks.course.ej.functional.lambdas.methodReference.model.Product;

import java.math.BigDecimal;

/*
* Interface funcional para referenciar o construtor de uma classe e obter instâncias a partir dela.
*/

@FunctionalInterface
public interface ProductFunctionCreate {

    // Argumentos devem corresponder ao construtor da classe:
    Product createNewProduct(String description, BigDecimal value, boolean openBox);
}
