import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * Para serializarmos um objeto, podemos usar um {@link ObjectOutputStream},
 * em conjunto com a API de IO clássica ou a NIO.
 *
 * Podemos passar por exemplo um fluxo de saída para um arquivo com {@link FileOutputStream}.
 */
public class SerializandoParaArquivo {
    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario("João",
                LocalDate.of(2005, Month.MARCH, 2),
                new BigDecimal("25000"));

        System.out.println(funcionario);

        Path path = Path.of("objetos/joao.dat");

        // Utilizando API clássica de IO
//        try (var outputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
//            outputStream.writeObject(funcionario);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // Utilizando NIO com API clássica de IO
        try (var outputStream = new ObjectOutputStream(Files.newOutputStream(path, CREATE, WRITE))) {
            outputStream.writeObject(funcionario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}