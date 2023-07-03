import com.github.leoarj.algaworks.course.ej.polimorfism.abstraction.GestorDeImpostos;
import com.github.leoarj.algaworks.course.ej.polimorfism.abstraction.PessoaFisica;

public class Main {
    public static void main(String[] args) {
        var gestorImpostos = new GestorDeImpostos();

        var joao = new PessoaFisica("João", 4_500);
        var maria = new PessoaFisica("Maria", 18_000);

        gestorImpostos.adicionarPessoa(joao);
        gestorImpostos.adicionarPessoa(maria);

        System.out.printf("Total de impostos: %.2f", gestorImpostos.getValorTotalImpostos());
    }
}