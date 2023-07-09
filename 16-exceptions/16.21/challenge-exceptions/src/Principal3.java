import com.github.leoarj.algaworks.course.ej.exceptions.ContaCorrente;
import com.github.leoarj.algaworks.course.ej.exceptions.ContaCorrenteException;

public class Principal3 {

    public static void main(String[] args) {
        try {
            ContaCorrente conta1 = new ContaCorrente("123");
            ContaCorrente conta2 = new ContaCorrente("987");

            conta1.ativar();
            conta2.ativar();

            conta1.depositar(1_000);
            conta1.transferir(conta2, 1_200);

            System.out.printf("Saldo da conta 1: %.2f%n", conta1.getSaldo());
            System.out.printf("Saldo da conta 2: %.2f%n", conta2.getSaldo());
        } catch (ContaCorrenteException e) {
            System.err.println("Ocorreu um erro ao realizar a operação:");
            //System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

}