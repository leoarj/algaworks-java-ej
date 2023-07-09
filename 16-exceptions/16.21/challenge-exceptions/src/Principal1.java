import com.github.leoarj.algaworks.course.ej.exceptions.ContaCorrente;
import com.github.leoarj.algaworks.course.ej.exceptions.ContaCorrenteDepositarException;
import com.github.leoarj.algaworks.course.ej.exceptions.ContaCorrenteException;
import com.github.leoarj.algaworks.course.ej.exceptions.ContaCorrenteInativaException;

public class Principal1 {

    public static void main(String[] args) {
        try {
            ContaCorrente conta1 = new ContaCorrente("123");
            
            conta1.depositar(1_000);

            System.out.printf("Saldo da conta 1: %.2f%n", conta1.getSaldo());
        } catch (ContaCorrenteException e) {
            System.err.println("Ocorreu um erro ao realizar a operação:");
            //System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
