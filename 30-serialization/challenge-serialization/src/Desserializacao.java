import com.algaworks.crm.Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.READ;

public class Desserializacao {

    public static void main(String[] args) {
        Path pathSerializacao = Path.of("objetos");
        Path pathSerializacaoCliente = pathSerializacao.resolve("Cliente.dat");
        // Para testar desserialização com outra classe
        //Path pathSerializacaoCliente = pathSerializacao.resolve("Endereco.dat");

        // DONE implementar desserialização
        Cliente cliente = desserializarObjeto(pathSerializacaoCliente, Cliente.class);

        System.out.println(cliente);
    }

    private static <T> T desserializarObjeto(Path pathSerializacaoObjeto, Class<T> clazz) {
        Object objetoDesserializado = null;

        try (var inputStream = new ObjectInputStream(Files.newInputStream(pathSerializacaoObjeto, READ))) {
            objetoDesserializado = inputStream.readObject();

            if (clazz.isInstance(objetoDesserializado)) {
                return clazz.cast(objetoDesserializado);
            }

            throw new ClassCastException(
                    String.format("Objeto serializado da classe: '%s' não pode ser desserializado para a classe '%s'",
                    objetoDesserializado.getClass().getName(), clazz.getName()));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}