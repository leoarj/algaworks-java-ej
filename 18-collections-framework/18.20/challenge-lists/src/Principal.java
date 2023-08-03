import com.github.leoarj.algaworks.course.ej.collections.agencia.CadastroPacoteViagem;
import com.github.leoarj.algaworks.course.ej.collections.agencia.PacoteViagem;
import com.github.leoarj.algaworks.course.ej.collections.agencia.exceptions.PacoteViagemExistenteException;
import com.github.leoarj.algaworks.course.ej.collections.agencia.exceptions.PacoteViagemNaoEncontradoException;

import java.util.List;

public class Principal {

    public static void main(String[] args) {
        CadastroPacoteViagem cadastro = new CadastroPacoteViagem();
        try {
            cadastro.adicionar("Istambul e Capadócia (20 noites)", 18_000);
            cadastro.adicionar("Neve em Bariloche (10 noites)", 11_000);
            cadastro.adicionar("Disney (10 noites)", 20_000);
            cadastro.adicionar("Natal Luz em Gramado (5 noites)", 8_500);
        } catch (PacoteViagemExistenteException e) {
            throw new RuntimeException(e);
        }

        try {
            cadastro.removerPorDescricao("Disney (7 noites)");
        } catch (PacoteViagemNaoEncontradoException e) {
            System.err.println(e.getMessage());
        }

        cadastro.ordenar();
        imprimirPacotes(cadastro.obterTodos());
        cadastro.ordenarPorPrecoDecrescente();
        imprimirPacotes(cadastro.obterTodos());

        PacoteViagem pacoteEncontrado = null;
        try {
            pacoteEncontrado = cadastro.buscarPorDescricao("Disney (7 noites)");
            System.out.println(pacoteEncontrado);
        } catch (PacoteViagemNaoEncontradoException e) {
            System.err.println(e.getMessage());
        }

        imprimirPacotes(cadastro.obterTodos());
    }

    private static void imprimirPacotes(List<PacoteViagem> pacotes) {
        // DONE iterar nos pacotes e imprimir descrição e preço

        for (PacoteViagem pacoteViagem : pacotes) {
            System.out.printf("Pacote de Viagem %s, Preço por pessoa: %.2f%n",
                    pacoteViagem.getDescricao(), pacoteViagem.getPrecoPorPessoa());
        }
    }
}