# Manipulando arquivos com NIO e NIO2
- API clássica (`java.io`) utiliza o modelo *blocking* (bloqueante).
>Em sistema com alto volume de operações concorrentes pode ter impacto do desempenho.

- NIO = *Non-Blocking IO*
    - A partir do Java 1.4
    - Permite diversas operações de IO não-bloqueantes.
    - API de mais baixo nível.

- NIO.2
- A partir do Java 1.7
- Mais fácil de usar, pois possui uma API mais fluente.

## Classe `Path` (`java.nio.Path`)
Classe que representa um caminho no sistema de arquivos, para um objeto do sistema de arquivos (arquivo, diretório).

Não tem métodos de manipulação, é uma abstração para um caminho no sistema de arquivos, mas não executa ações sobre ele.

### Métodos
- Criar uma representação do caminho de um arquivo
    - Java 1.7 - É necessário usar as classes `java.nio.file.FileSystem` e `java.nio.file.FileSystems` para recuperar um `Path`
        - A classe `FileSystem` é uma abstração para um sistema de arquivos.
        - A classe `FileSystems` é a classe com métodos de fábrica para recuperar objetos de implementações de `FileSystem`.
    ```java
    import java.nio.file.FileSystem;
    import java.nio.file.FileSystems;
    import java.nio.file.path;

    //...

        // Recupera implementação de sistema de arquivos
        FileSystem fs = FileSystems.getDefault();

        // Retorna uma representação de um path para um arquivo
        Path pathArquivo = fs.getPath("/Users/leandro/Documents/contrato.txt");

        // Retorna uma representação de um path para um arquivo,
        // passando parâmetros adicionais que serão concatenados no caminho,
        // já com delimitador de path correspondente ao OS
        Path pathArquivo = fs.getPath("/Users/leandro", "Documents", "contrato.txt");
    ```
    - Java 1.11
        - Adicionado método `Path.of(String first, String... more)`
        ```java
        Path pathArquivo = Path.of("/Users/leandro", "Documents", "contrato.txt");
        ```
- `Path.getFileName()`
    >Recupera nome do arquivo presente no *path*.

- `pathArquivo.getParent();`
    >Recupera pasta anterior, onde outra pasta ou arquivo estão contidos.

- `java.nio.Path.toFile()`;
    > Retorna uma instância de `java.io.File` com base em uma instância de `Path`.

- `java.io.File.toPath()`;
    > Retorna uma instância de `java.nio.Path` com base em uma instância de `File`.

## Caminhos absolutos e relativos com `java.nio.Path`
- `Path.toAbsolutePath()`
    >Retorna o caminho absoluto de um arquivo (relativo por exemplo).
- `Path.normalize()`
    >Normaliza um caminho, removendo elementos redundantes,
    como representações para o diretório atual `.` (ponto) e diretório pai `..` (dois pontos), na verdade, traduzindo esses elementos em um caminho final.
- `Path.isAbsolute()`
    >Diz se um objeto `Path` se refere a um caminho absoluto.
- `Path.resolve(Path other)`
    >Combina *paths (pastas e/ou arquivos)*, gerando um outro path resolvido,
    com o caminho dos *paths* combinados anteriormente.

## Operações com `java.nio.Files`
Classe utilitária com métodos estáticos, para manipulação de arquivos.

- `Path createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException`
    >Cria o último diretório de uma hierarquia, com base em um objeto `Path`.
- `Path createDirectories(Path dir, FileAttribute<?>... attrs) throws IOException`
    >Cria todos os diretório de uma hierarquia, com base em um objeto `Path`.
- `Files.createFile(Path path, FileAttribute<?>... attrs)`
    >Cria um arquivo no sistema de arquivos, com base em um objeto `Path`.

## Copiando arquivos e diretórios
- `public static Path copy(Path source, Path target, CopyOption... options) throws IOException`
    >Realiza a cópia de pastas/arquivos para um destinos,
    especificados por objetos `Path`.
    - No caso de cópia de arquivos, ocorrerá falha caso o mesmo já exista,
    caso for necessária a substituição, deve-se passar um `java.nio.file.StandardCopyOption.REPLACE_EXISTING` como argumento, por exemplo.
        ```java
        Files.copy(arquivoContratoOrigem, arquivoContratoDestino, REPLACE_EXISTING);
        ```
    - No caso da cópia de pastas, o método não copiará o conteúdo, sendo necessário uma operação a mais *(walk file tree)*.

## Movendo arquivos e diretórios
- `public static Path move(Path source, Path target, CopyOption... options) throws IOException`
    >Move um arquivo de uma pasta para outra ou o conteúdo de uma pasta para outra, deixando de existir a original (como se fosse renomeada).

## Excluindo arquivos e diretóros
- `public static void delete(Path path) throws IOException`
    >Exclui um arquivo ou pasta, se for uma pasta, a mesma deve estar vazia.
- `public static boolean deleteIfExists(Path path) throws IOException`
    >Exclui um arquivo ou pasta (não lança exception se o arquiv já existir),
    se for uma pasta, a mesma deve estar vazia.
- No caso deleção recursiva, é necessário uma operação a mais *(walk file tree)*.

## Operações com `Files.walkFileTree()`

### Métodos de *callback*

```java
public static Path walkFileTree(Path start,
                                FileVisitor<? super Path> visitor)
                            throws IOException
```

Esse método da classe `java.nio.Files` é utilizado para percorrer a árvore de arquivos, onde podemos executar operações, como exclusão por exemplo.

- `Path start`
    >Como argumento inicial é passado o *path* inicial (pasta) a partir de onde os arquivos serão percorridos.
- `FileVisitor<? super Path> visitor`
    >Objeto com implementação de métodos de *callback* que serão chamados pelo método de percorrimento `walkFileTree`.
    - Uma implementação padrão da interface `FileVisitor` é a `java.nio.SimpleFileVisitor`.
        - Com ela, podemos sobreescrever os métodos de *callback* para
        incluírmos lógica para manipulação dos arquivos/pastas.
        - Seus métodos retornar um `FileVisitResult`,
        que é um *enum*, com as constantes que podemos usar no resultado
        de algum método de *callback* sobrescrito.
            - `CONTINUE`
                >Continua o processamento na árvore.
            - `SKIP_SIBLINGS`
                >Continua o processamento, mas não visita os objetos no mesmo nível (objetos irmãos).
            - `SKIP_SUBTREE`
                >Continua o processamento sem acessar sub-árvores (diretórios).
            - `TERMINATE`
                >Finaliza o processamento.

### Exemplo de exclusão de arquivos com `walkFileTree`
```java
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Principal {

    public static void main(String[] args) throws IOException {
        Path pastaDocs = Path.of("docs");

        Files.walkFileTree(pastaDocs, new SimpleFileVisitor<>() {
            /**
             * Sobreescrita para método que visita o arquivo
             * (momento da execução).
             * No caso, está excluindo o arquivo.
            */
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.printf("Excluindo arquivo: %s%n", file);
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            /**
             * Sobreescrita para método que executa após visitar um diretório
             * (após processar arquivos e sub-ávores no diretório corrente).
             * Nesse caso, está excluindo o diretório vazio,
             * já que o "visitFile" executou a lógica de exclusão referente a arquivos.
            */
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.printf("Excluindo diretório: %s%n", dir);
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
```

## Obtendo informações de arquivos e diretórios
A partir de um objeto `Path`, é possível perguntar à classe `Files` sobre alguns atributos de arquivos e diretórios.

- `public static long size(Path path) throws IOException`
    >Retorna em bytes o tamanho deu um arquivo/diretório.
- `public static boolean isRegularFile(Path path, LinkOption... options)`
    >Retorna se o `Path` se refere a um arquivo regular ou não.
- `isDirectory(Path path, LinkOption... options)`
    >Retorna se o `Path` se refere a uma pasta ou não.
- `getLastModifiedTime(Path path, LinkOption... options)`
    >Retorna um objeto `FileTime`referente a atributo de data/hora de um objeto do sistema de arquivos.
    - `toInstant()`
        >Obtém uma instância de `java.time.Instant` a partir de um `FileTime`.
- `isHidden(Path path)`
    >Retorna se o arquivo é oculto.
- `isReadable(Path path)`
    >Retorna se possui ptermissão de leitura para o arquivo.
- `isWritable(Path path)`
    >Retorna se possui permissão de escrita para o arquivo.
- `isExecutable(Path path)`
    >Retorna se possui permissão de execução para o arquivo e/ou se é executável.

## Listando conteúdo de diretórios
A partir da classe `Files` é possível listar o conteúdo de diretórios,
além disso, é possível aliar o uso de *Streams* para fluxos de condições de listagem.

`public static Stream<Path> list(Path dir) throws IOException`
    >Retorn um `Stream` de `Path`, com os conteúdos de um diretório
    (de forma não recursiva).

- Exemplo de listagem, aliado ao uso de *Stream API*
    ```java
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;

    public class Main {
        public static void main(String[] args) throws IOException {
            // "Lista arquivos da pasta docs (no primeiro nível)
            Path pasta = Path.of("docs");

            /*
            * Com apoio de streams:
            * - Filtra se é um arquivo,
            * - Recupera o nome (retornado como um Path)
            * - Converte para um Stream de String
            * - Aplica filtro para arquivos somente com extensão .txt
            * - Imprime o nome dos arquivos.
            * */
            try (var stream = Files.list(pasta)) {
                stream.filter(Files::isRegularFile)
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .filter(nome -> nome.matches("(?i)^.*\\.txt"))
                        .forEach(System.out::println);
            }
        }
    }
    ```

## Pesquisando arquivos em pastas e subpastas
- Método `find` da classe `Files`
    ```java
    public static Stream<Path> find(Path start,
                                    int maxDepth,
                                    BiPredicate<Path,BasicFileAttributes> matcher,
                                    FileVisitOption... options)
                                throws IOException
    ```
    >Recebe um `Path`, uma função de bi-predicado e o número máximo de níveis (maxDepth) que serão acessados,
    retornando um `Stream` dos arquivos que atendem as condições.

    - Exemplo para busca de arquivos
    ```java
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.attribute.BasicFileAttributes;
    import java.util.function.BiPredicate;

    public class Main2 {

        public static void main(String[] args) throws IOException {
            Path pasta = Path.of("docs");

            try (var stream = Files.find(pasta, Integer.MAX_VALUE,
                    Main2::testarExtensaoArquivoTxt)) {
                stream.forEach(System.out::println);

            }
        }

        private static boolean testarExtensaoArquivoTxt(Path path, BasicFileAttributes basicFileAttributes) {
            return path.getFileName().toString()
                    .matches("(?i)^.*\\.txt");
        }
    }
    ```

## *Buffers* e a classe `ByteBuffer`
- *Buffer*
    >Área de armazenamento temporário, para guardar dados que estão sendo transferidos/processados.

Podemos manipular *buffers* de *bytes* com a classe `ByteBuffer` do pacote `java.nio`.

### Métodos
- `public static ByteBuffer allocate(int capacity)`
    >Aloca e retorna um buffer de bytes com uma capacidade de bytes fornecida.
- `public final int limit()` (`java.nio.Buffer`)
    >Retorna o limite do buffer, quantas posições podem ser preenchidas independente da capacidade.
- `public abstract ByteBuffer put(byte b)`
    >Escreve o byte na posição atual e incrementa a posição.
- `public ByteBuffer flip()` 
    >Altera o limite do buffer para a posição atual e zera a posição.
    - Por exemplo, um buffer com limite inicial de 1024 posições,
    com 2 posições preenchidas, após a chamada do `flip()` terá:
        - Limite = 2 (posição atual era 2).
        - Posição = 0.
        - Capacidade = 1024 (se mantém, mas o que conta ao adicionar elementos é o `limit()`).
- `public ByteBuffer clear()`
    >"Limpa" o *buffer*/retorna um `ByteBuffer` com limite igual a zero e capacidade igual ao limite do buffer.
- `public final byte[] array()` (`java.nio.ByteBuffer`)
    >Retorna um *array* de bytes referente ao buffer.

## Buffers e a classe `CharBuffer`
É possível trabalhar com *buffers* de caracteres, onde cada posição será ocupada por um `char`.

Possui métodos parecidos com `ByteBuffer`, porém voltado a caracteres.

### Métodos
- `public static CharBuffer allocate(int capacity)`
    >Aloca e retorna um buffer de caracteres com uma capacidade de caracteres fornecida.
- `public final CharBuffer flip()`
    >Realiza o mesmo procedimento que `flip()` de `ByteBuffer`.
- `default IntStream chars()` (porque `CharBuffer` implementa `java.lang.CharSequence`)
    >Retorna um `IntStream` com inteiros referentes aos caracteres contidos no *buffer*.<br>
    Para representar os valores como caracteres, deve-se fazer um *cast* para `char`.

## Decodificando `ByteBuffer` em `CharBuffer`
- `public final CharBuffer decode(ByteBuffer bb)`
    >Decodifica um `ByteBuffer` em um `CharBuffer` utilizando o *chartset* **UTF-8**.

- Exemplo
    ```java
    import java.nio.ByteBuffer;
    import java.nio.CharBuffer;
    import java.nio.charset.Charset;
    import java.nio.charset.StandardCharsets;
    import java.util.Arrays;

    public class Main3 {

        public static void main(String[] args) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // Olá
            buffer.put((byte) 79);
            buffer.put((byte) 108);
            buffer.put((byte) -61);
            buffer.put((byte) -95);

            // Prepara o buffer para leitura
            buffer.flip();

            // Pode retornar com algum charset arbitrário
    //        CharBuffer charBuffer = buffer.asCharBuffer();
            /*
            * Decodifica o ByteBuffer em um CharBuffer de acordo com o UTF8
            */
            Charset charset = StandardCharsets.UTF_8;
            CharBuffer charBuffer = charset.decode(buffer);
            System.out.println(charBuffer);

            // Para obter os códigos dos caracteres em UTF-8
    //        System.out.println(Arrays.toString("Olá".getBytes(StandardCharsets.UTF_8)));
        }
    }

    ```
## Leitura de arquivos com `ByteChannel` (`java.nio.channels`)
No conceito de **IO** *Channels* são como uma conexão a uma fonte de dados,
para manipulação como leitura e/ou escrita, de modo que diferente de *Streams* comuns,
eles suportam ser bidirecionais.

Em Java, os *channels* são orientados ao *buffers*, lendo e escrevendo informações em blocos.

- Exemplo de uso da classe `ByteChannel`
    ```java
    import java.io.IOException;
    import java.nio.ByteBuffer;
    import java.nio.CharBuffer;
    import java.nio.channels.ByteChannel;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.StandardOpenOption;

    public class Main4 {

        public static void main(String[] args) throws IOException {
            Path path = Path.of("docs/poema.txt");
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            try (ByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ)) {
                // Lê um bloco de bytes conforme a capacidade do buffer e coloca nele a informação.
                channel.read(buffer);

                buffer.flip();
                CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
                System.out.println(charBuffer);
            }
        }

    }
    ```

## Leitura de arquivos de texto com *buffers* menores
Ao se ler arquivos de texto com buffers de bytes,
deve-se levar em conta que podemos ter problemas de codificação,
caso a capacidade do buffer seja menor e em algum ponto haja algum caractere especial que seja representado por mais de um byte.

- Exemplo
    ```java
    import java.io.IOException;
    import java.nio.ByteBuffer;
    import java.nio.CharBuffer;
    import java.nio.channels.ByteChannel;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.StandardOpenOption;

    public class Main5 {

        public static void main(String[] args) throws IOException {
            Path path = Path.of("docs/poema.txt");
            ByteBuffer buffer = ByteBuffer.allocate(3); // alocando capacidade menor

            try (ByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ)) {
                // flip() retorna a quantidade de bytes lidos
                while (channel.read(buffer) > 0) {
                    buffer.flip();

                    CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
                    System.out.print(charBuffer);

                    buffer.clear();
                }
            }
        }

    }
    ```

## Escrita de arquivos com `ByteChannel`
Para escrevermos em um arquivo com `ByChannel`, os passos são semelhantes à leitura,
com a diferença de passar as opções para escrita.

- Exemplo
    ```java
    import java.io.IOException;
    import java.nio.ByteBuffer;
    import java.nio.channels.ByteChannel;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.Arrays;

    import static java.nio.file.StandardOpenOption.*;

    public class Main6 {

        public static void main(String[] args) throws IOException {
            Path path = Path.of("docs/arquivo.txt");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put((byte) 79);
            buffer.put((byte) 108);
            buffer.put((byte) -61);
            buffer.put((byte) -95);

            System.out.println(Arrays.toString(buffer.array()));

            try (ByteChannel channel = Files.newByteChannel(
                    path, WRITE, CREATE, TRUNCATE_EXISTING)) {
                buffer.flip();
                channel.write(buffer);
            }
        }
    }
    ```

## API de I/O clássica e a API NIO
Em alguns casos os pacotes `java.io` e `java.nio` interagem,
para questões de compatibilidade e métodos de conveniência.

- Exemplo
    ```java
    import java.io.BufferedReader;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.nio.ByteBuffer;
    import java.nio.CharBuffer;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.Files;
    import java.nio.file.Path;

    public class Main7 {

        public static void main(String[] args) throws IOException {
            Path path = Path.of("docs/poema.txt");

            /*
            * Exemplo de integração entre BufferedReader (java.io) e Files (java.nio).
            * No caso, Files criando um novo BufferedReader a partir de um Path.
            */
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                reader.lines()
                        .forEach(System.out::println);
            }

            /*
            * Exemplo de integração entre InputStream (java.io) e Path.
            * No caso, Path convertendo para File (java.io) para compatibilidade com InputStream.
            */
            ByteBuffer buffer = ByteBuffer.allocate(40);
            try (var inputStream = new FileInputStream(path.toFile());
                var channel = inputStream.getChannel()) {
                while (channel.read(buffer) > 0) {
                    buffer.flip();
                    CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
                    System.out.println(charBuffer);
                    buffer.clear();
                }
            }
        }

    }
    ```

## Leitura de arquivos com `Files`
Simplifica a leitura de arquivos, mas deve ser usado com critério (usar de preferência com arquivos pequenos),
já que lê todo o conteúdo do arquivo.

- Exemplo
    ```java
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.Arrays;

    public class Main8 {

        public static void main(String[] args) throws IOException {
            Path path = Path.of("docs/poema.txt");

            /*
            * Lê todas as linhas do arquivo como um Stream (java.util.Stream),
            * e mantém o arquivo aberto até o fechamento stream,
            * por isso deve ser usado com try-with-resource ou chamando close() no finally.
            */
            try (var stream = Files.lines(path)) {
                stream.forEach(System.out::println);
            }

            /*
            * Lê todas as linhas do arquivo retornando como uma lista de Strings.
            * Já fecha os recursos internamente, não necessitando de try-with-resources.
            */
            Files.readAllLines(path)
                    .forEach(System.out::println);

            /*
            * Lê todo o conteúdo do arquivo para uma String.
            * Já fecha os recursos internamente, não necessitando de try-with-resources.
            */
            String conteudo = Files.readString(path);
            System.out.println(conteudo);

            /*
            * Lê todos os bytes de um arquivo, retornando um array de bytes.
            * Já fecha os recursos internamente, não necessitando de try-with-resources.
            */
            byte[] bytes = Files.readAllBytes(path);
            System.out.println(Arrays.toString(bytes));
        }

    }
    ```

## Escrita de arquivos com `Files`
- Exemplo
    ```java
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.Arrays;

    public class Main9 {

        public static void main(String[] args) throws IOException {
            Path path = Path.of("docs/arquivo.txt");

            // Escreve uma String diretamente
            Files.writeString(path, "Opa!");

            // Escreve uma lista de Strings, cada elemento vai ser separado por quebra de linha
            var linhas = Arrays.asList("Opa!", "Beleza, mergulhador?");
            Files.write(path, linhas);

            // Escreve diretamente bytes no arquivo
            byte[] bytes = { 79, 108, -61, -95 };
            Files.write(path, bytes);
        }

    }
    ```

Desafio

Refatorar projeto feito no módulo de IO para NIO
