package com.github.leoarj.algaworks.course.ej.functional.lambdas.methodReference.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Product {

    private static final Random RANDOM = new Random();

    private String description;
    private BigDecimal value;
    private boolean openBox;

    // Para testar constructor references
    public Product(String description) {
        this.description = description;
        this.value = new BigDecimal("0");
        this.openBox = false;
    }

    public Product(String description, BigDecimal value, boolean openBox) {
        this.description = description;
        this.value = value;
        this.openBox = openBox;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isOpenBox() {
        return openBox;
    }

    public void makeOpenBox() {
        this.openBox = true;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", value=" + value +
                ", openBox=" + openBox +
                '}';
    }

    public static List<Product> getProductList() {
        var products = new ArrayList<Product>();
        products.add(new Product("Laptop", new BigDecimal("1200"), false));
        products.add(new Product("Smartphone", new BigDecimal("900"), true));
        products.add(new Product("Watch", new BigDecimal("850"), true));
        products.add(new Product("TV", new BigDecimal("2300"), false));
        products.add(new Product("Microwave", new BigDecimal("460"), true));

        return products;
    }

    public static Product getRandomProduct() {
        var description = String.format("Random product %d", RANDOM.nextInt(1, 100));
        var value = BigDecimal.valueOf(RANDOM.nextDouble(10, 5000)).setScale(2, RoundingMode.HALF_UP);
        var isOpenBox = RANDOM.nextBoolean();

        return new Product(description, value, isOpenBox);
    }
}