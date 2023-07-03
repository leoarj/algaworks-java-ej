import com.github.leoarj.algaworks.course.ej.polimorfism.abstraction.EmpresaLucroReal;
import com.github.leoarj.algaworks.course.ej.polimorfism.abstraction.EmpresaSimples;
import com.github.leoarj.algaworks.course.ej.polimorfism.abstraction.GestorDeImpostos;
import com.github.leoarj.algaworks.course.ej.polimorfism.abstraction.PessoaFisica;

public class Main2 {
    public static void main(String[] args) {
        var gestorImpostos = new GestorDeImpostos();

        var joao = new PessoaFisica("João", 45_000);
        var maria = new PessoaFisica("Maria", 180_000);

        var lanchonete = new EmpresaSimples("Lanchonete", 250_000, 160_000);
        var postoDeCombustivel = new EmpresaLucroReal("Posto da Rodovia", 8_000_000, 6_000_000);

        gestorImpostos.adicionarPessoa(joao);
        gestorImpostos.adicionarPessoa(maria);
        gestorImpostos.adicionarPessoa(lanchonete);
        gestorImpostos.adicionarPessoa(postoDeCombustivel);

        System.out.printf("Total de impostos: %.2f", gestorImpostos.getValorTotalImpostos());
    }
}