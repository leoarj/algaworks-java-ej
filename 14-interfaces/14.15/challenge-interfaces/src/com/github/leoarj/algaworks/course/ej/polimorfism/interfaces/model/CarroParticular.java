package com.github.leoarj.algaworks.course.ej.polimorfism.interfaces.model;

public class CarroParticular extends VeiculoAutomotor {

    public static final double PERCENTUAL_PREMIO_VALOR_MERCADO_CARRO_PARTICULAR = 0.04;
    public static final int ANO_TAXA_ADICIONAL_PREMIO_CARRO_PARTICULAR = 2000;
    public static final double TAXA_ADICIONAL_ANO_PREMIO_CARRO_PARTICULAR = 0.5;

    public CarroParticular(String modelo, double valorMercado, int anoFabricacao) {
        super(modelo, valorMercado, anoFabricacao);
    }

    @Override
    public double calcularValorPremio() {
        double valorPremio = 0;

        valorPremio = getValorMercado() * PERCENTUAL_PREMIO_VALOR_MERCADO_CARRO_PARTICULAR;
        if (getAnoFabricacao() < ANO_TAXA_ADICIONAL_PREMIO_CARRO_PARTICULAR) {
            valorPremio *= TAXA_ADICIONAL_ANO_PREMIO_CARRO_PARTICULAR;
        }

        return valorPremio;
    }

    @Override
    public String descrever() {
        return String.format("Carro particular %s ano %d, avaliado em R$%.2f",
                getModelo(), getAnoFabricacao(), getValorMercado());
    }
}
