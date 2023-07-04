package com.github.leoarj.algaworks.course.ej.polimorfism.interfaces.model;

public class ImovelResidencial implements BemSeguravel {

    public static final double PERCENTUAL_PREMIO_VALOR_MERCADO_IMOVEL_RESIDENCIAL = 0.001;
    public static final double TAXA_ADICIONAL_PREMIO_AREA_CONSTRUIDA = 0.30;

    private double valorMercado;
    private int areaConstruida;

    public ImovelResidencial(double valorMercado, int areaConstruida) {
        this.valorMercado = valorMercado;
        this.areaConstruida = areaConstruida;
    }

    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double valorMercado) {
        this.valorMercado = valorMercado;
    }

    public int getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(int areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    @Override
    public double calcularValorPremio() {
        double valorPremio = 0;

        valorPremio = getValorMercado() * PERCENTUAL_PREMIO_VALOR_MERCADO_IMOVEL_RESIDENCIAL;
        valorPremio += getAreaConstruida() * TAXA_ADICIONAL_PREMIO_AREA_CONSTRUIDA;

        return valorPremio;
    }

    @Override
    public String descrever() {
        return String.format("Imóvel residencial com %dm2 de área construída, avaliado em R$%.2f",
                getAreaConstruida(), getValorMercado());
    }
}
