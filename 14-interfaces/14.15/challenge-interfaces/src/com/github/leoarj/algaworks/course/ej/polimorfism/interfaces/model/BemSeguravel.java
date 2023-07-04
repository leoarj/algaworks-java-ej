package com.github.leoarj.algaworks.course.ej.polimorfism.interfaces.model;

public interface BemSeguravel {

    double calcularValorPremio();
    String descrever();

    /*
    * Default methods são comportamentos padrão que podemos
    * incluir nas interfaces, utilizando a palavra "default".
    *
    * Classes já criadas que implementam a interface ficam desobrigadas
    * a implementar o método em questão, porém o mesmo pode ser sobrescrito se quiser.
    *
    * O seu uso é indicado quando não é possível modificar/ter controle de
    * quais classes já estão implementando a interface (código distribuído em bibliotecas
    * para uso por outros desenvolvedores, por exemplo), a fim de evitar quebra da compilação.
    *
    * Quando possível, preferir o uso de classes abstratas base em conjunto com interfaces.
    */

    default String testeDefault() {
        return "Teste de método default em interfaces";
    }
}
