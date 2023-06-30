package composition;

public class Holerite {

    Funcionario funcionario;
    double valorTotalHorasNormais;
    double valorTotalHorasExtras;
    double valorAdicionalFilhos;

    double calcularValorTotal() {
        //double valorTotal = 0;

        var valorTotal = valorTotalHorasNormais + valorTotalHorasExtras + valorAdicionalFilhos;

        return valorTotal;
    }

    void imprimir() {
        System.out.printf("Funcionário %s.%n" +
                "Valor Horas Normais: %.2f.%n" +
                "Valor Horas Extras: %.2f.%n" +
                "Valor Adicional Filhos: %.2f.%n" +
                "Valor Total Holerite: %.2f.",
            funcionario.nome,
            valorTotalHorasNormais,
            valorTotalHorasExtras,
            valorAdicionalFilhos,
            calcularValorTotal());
    }
}