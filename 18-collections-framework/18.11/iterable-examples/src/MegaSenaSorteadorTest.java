import com.github.leoarj.algaworks.course.ej.collections.iterables.MegaSenaSorteador;

import java.util.SortedSet;

public class MegaSenaSorteadorTest {

    public static void exibirJogos() {
        MegaSenaSorteador megaSenaSorteador = new MegaSenaSorteador(10);

        // Forma mais curta para realizar a iteração.
        for (SortedSet<Integer> jogo : megaSenaSorteador) {
            System.out.println(jogo);
        }

        // Essa forma também é válida, porém com um pouco mais de código.
//        Iterator<SortedSet<Integer>> iterator = megaSenaSorteador.iterator();
//
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        System.out.println(iterator.next()); // Causa uma NoSuchElementException.
    }

    public static void main(String[] args) {
        exibirJogos();
    }
}
