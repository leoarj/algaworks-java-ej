package com.github.leoarj.algaworks.course.ej.polimorfism.interfaces.model;

public class Caminhao extends VeiculoAutomotor {

    public static final double PERCENTUAL_PREMIO_VALOR_MERCADO_CAMINHAO = 0.02;
    public static final double TAXA_ADICIONAL_PREMIO_EIXOS_CAMINHAO = 50.0;
    private int quantidadeEixos;

    public Caminhao(String modelo, double valorMercado, int anoFabricacao, int quantidadeEixos) {
        super(modelo, valorMercado, anoFabricacao);
        this.quantidadeEixos = quantidadeEixos;
    }

    public int getQuantidadeEixos() {
        return quantidadeEixos;
    }

    public void setQuantidadeEixos(int quantidadeEixos) {
        this.quantidadeEixos = quantidadeEixos;
    }

    @Override
    public double calcularValorPremio() {
        double valorPremio = 0;

        valorPremio = getValorMercado() * PERCENTUAL_PREMIO_VALOR_MERCADO_CAMINHAO;
        valorPremio += getQuantidadeEixos() * TAXA_ADICIONAL_PREMIO_EIXOS_CAMINHAO;

        return valorPremio;
    }

    @Override
    public String descrever() {
        return String.format("Caminhão %s ano %d, %d eixos, avaliado em R$%.2f",
                getModelo(), getAnoFabricacao(), getQuantidadeEixos(), getValorMercado());
    }
}
