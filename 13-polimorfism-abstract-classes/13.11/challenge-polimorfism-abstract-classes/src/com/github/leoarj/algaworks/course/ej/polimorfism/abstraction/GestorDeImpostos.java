package com.github.leoarj.algaworks.course.ej.polimorfism.abstraction;

public class GestorDeImpostos {

    private double valorTotalImpostos;

    public double getValorTotalImpostos() {
        return valorTotalImpostos;
    }

    public void adicionarPessoa(Pessoa pessoa) {
        double valorImpostos = pessoa.calcularImpostos();
        valorTotalImpostos += valorImpostos;
        System.out.printf("Impostos devidos de %s = %.2f%n", pessoa.getNome(), valorImpostos);

        /*
        * Pattern Matching para operador instanceof
        * A partir do Java 16 foi introduzido um recurso para facilitar o uso do operador instanceof,
        * onde já é possível na mesma condição informar a variável onde vai ser injetada a referência do casting.
        *
        * Por exemplo, caso não for possível modificar as superclasses por algum motivo (código externo de
        * bibliotecas, regras de desenvolvimento da empresa etc) e não puder introduzir métodos que façam melhor uso do
        * polimorfismo a fim de reduzir o uso do operador instanceof, então nessa situação é possível pelo menos
        * facilitar o uso do instanceof caso tenha que chamar algum método específico de alguma subclasse/classe
        * concreta.
        */

        // Observar que sempre tentar usar instanceof somente quando necessário para não perder os benefícios
        // do polimorfismo e abstração (Se possível declarar métodos abstratos na classe base que digam (tell)
        // alguma condição ou valor de modo mais abrangente).

        if (pessoa instanceof PessoaJuridica pessoaJuridica // <- Aqui está o pattern matching para instanceof
                && pessoaJuridica.getLucroAnual() > 0) {
            System.out.printf("Empresa de Lucro Real (%s) com lucro anual de = %.2f%n", pessoa.getNome(), pessoaJuridica.getLucroAnual());
        }
    }
}
