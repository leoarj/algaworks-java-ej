package composition;

public class Main {
    public static void main(String[] args) {
        var funcionario = new Funcionario();
        funcionario.nome = "João";
        funcionario.quantidadeFilhos = 2;

        ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
        contratoTrabalho.funcionario = funcionario;
        contratoTrabalho.valorHoraNormal = 150.00f;
        contratoTrabalho.valorHoraExtra = 175.00f;

        FolhaPagamento folhaPagamento = new FolhaPagamento();

        Holerite holerite = null;
        holerite = folhaPagamento.calcularSalario(contratoTrabalho, 40, 8);

        holerite.imprimir();

        FolhaPagamento2 folhaPagamento2 = new FolhaPagamento2();

        Holerite holerite2 = null;
        holerite2 = folhaPagamento2.calcularSalario(new CalcularSalarioContexto(contratoTrabalho, 40, 8));

        holerite2.imprimir();
    }
}