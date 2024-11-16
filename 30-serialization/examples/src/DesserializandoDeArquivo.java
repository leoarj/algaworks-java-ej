import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Para desserializar um objeto, podemos utilizar as classes {@link ObjectInputStream}
 * em conjunto com {@link java.io.FileInputStream}.
 * */
public class DesserializandoDeArquivo {

    public static void main(String[] args) {
        Path path = Path.of("objetos/joao.dat");

        try (var inputStream = new ObjectInputStream(Files.newInputStream(path, StandardOpenOption.READ))) {
            Funcionario funcionario = (Funcionario) inputStream.readObject();
            System.out.println(funcionario);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
