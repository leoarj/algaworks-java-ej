import com.algaworks.crm.Cliente;
import com.algaworks.crm.Endereco;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.WRITE;

public class Serializacao {

    public static void main(String[] args) {
        Endereco endereco = new Endereco("Rua das Ameixas", "1000", "Centro");
        Cliente cliente = new Cliente("João da Silva", endereco, new BigDecimal("100000"));

        System.out.println(cliente);

        Path pathSerializacao = Path.of("objetos");
        Path pathSerializacaoCliente = pathSerializacao.resolve("Cliente.dat");

        // DONE implementar serialização
        serializarObjeto(pathSerializacaoCliente, cliente);
    }

    private static void serializarObjeto(Path pathSerializacaoObjeto, Object objeto) {
        try (var outputStream = new ObjectOutputStream(
                Files.newOutputStream(pathSerializacaoObjeto, CREATE_NEW, WRITE))) {
            outputStream.writeObject(objeto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}