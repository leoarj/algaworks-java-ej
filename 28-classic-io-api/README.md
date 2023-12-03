# Java IO API (Clássica) - java.io

- IO
    >Input-Output (Entrada/Saída de dados).
- API clássica desde o Java 1.3.
    >Ainda muito utilizada (clássica mas não depreciada).

## Classe `File` (`java.io.File`)
Classe que representa um objeto do sistema de arquivos (arquivo, diretório).

Com ela é possível criar arquivos e pastas no sistema de arquivos do computador.

### Métodos
- `mdir()`
    >Cria um diretório raíz.
- `mkdirs()`
    >Cria uma estrutura de diretórios.
- `createNewFile()`
    >Cria um novo arquivo que foi especificado em um objeto de `File``.

### Obtendo caminho **absoluto** e **canônico**

- Caminho **absoluto**
    >Caminho que começa da raíz do sistema de arquivos.
- Caminho **relativo**
    >Caminho relativo a um diretório base.

#### Métodos
- `getAbsolutePaht()`
    >Resolve o caminho absoluto.
- `getAbsoluteFile()`
    >Retorna uma nova instância de File com caminho absoluto resolvido.

- Caminho **canônico**
    >Caminho absoluto resolvido para o verdadeiro caminho (final).

#### Métodos
- `getCanonicalPath()`
    >Resolve o caminho absoluto final.
- `getCanonicalFile()`
    >Retorna uma nova instância de `File` com caminho absoluto final resolvido.

## Manipulação de arquivos e pastas

### Renomear
- `File.renameTo()`
    >Renomeia um objeto do sistema de arquivos para outro nome.
    >Retorna boolean e recebe como argumento uma instância de um objeto File.

#### Em alguns casos pode falhar
- Em caso de arquivo bloqueado por outro processo ou pelo sistema operacional.
- Em caso de permissões insuficientes.
- Em caso de mover para outra partição ou armazenamento remoto.

*Não é uma operação **atômica** (não garante consistência, se alguma coisa ocorrer durante processo pode haver inconsistência no sistema de arquivos).*

### Mover
- `File.renameTo()`
    >Também pode ser utilizado para mover arquivos para um diferente caminho,
    >porém essa é uma operação dependente de sistema operacional.

### Excluir
- `File.delete()`
    >Não exclui uma pasta caso o mesma possua arquivos.

## Obtendo informações de arquivos e diretórios

- `File.getName()`
    >Obtém o nome do arquivo ou diretório.

- `File.getParent()`
    >Obtém o diretório pai do arquivo ou diretório atual.

- `File.isFile()`
    >Diz se o objeto representa um arquivo no sistema de arquivos.

- `File.isDirectory()`
    >Diz se o objeto representa uma pasta no sistema de arquivos.

- `File.exists()`
    >Diz se o objeto do sistema de arquivos realmente existe.

- `File.isHidden()`
    >Diz se o objeto do sistema de arquivos é oculto.

- `File.lastModified()`
    >Retorna em millesegundos a última modificação.

- `File.length()`
    >Retorna o tamanho do objeto do sistemam de arquivos em *bytes*.

- `File.canExecute()`
    >Diz se o arquivo pode ser executado.

- `File.canRead()`
    >Diz se o objeto do sistema de arquivos pode ser lido.

- `File.canWrite()`
    >Diz se pode executar escrita no objeto do sistema de arquivos.

## Listando arquivos e diretórios
- `File.listFiles()`
    >Retorna um array de objetos do sistema de arquivos.

*Não é recursivo, ou seja, lista apenas os objetos filhos no mesmo nível.*

### Filtrando listagem
A listagem dos objetos do sistema de arquivos podem ser filtrada através de expressões *lambda* e *method reference*.

Para isso, basta passar o filter como argumento no método `File.listFiles()`.

#### Exemplo
```java
// Com method reference (Referência à interface funcional FileFilter)
File[] listedFiles = files.listFiles(File::isFile);
```

```java
// Com expressões lambda para uma lógica mais
// específica (Referência à interface funcional FilenameFilter).
File[] listedFiles = files.listFiles((dir, name) -> name.startsWith("c"));
```

*Obs.: Esse método não é **null-safe**, então deve ser feita uma verificação do retorno antes de consumir os resultados.*

## I/O Streams
Existem duas formas de streams na API de I/O do Java.

### *Byte oriented* streams
São streams de mais baixo nível orietados diretamento no fluxo de bytes,
são a base para outros streams que abstraem dados mais complexos.

### *Character oriented* streams
São streams orientados a caracteres.

Utilizam os streams orientados a bytes, porém abstraem a manipulação e conversão
de bytes em caracteres e vice-versa, bem como questões de tabelas de condificação de caracteres
entre outros detalhes, como leitura de arquivos de texto linha-a-linha.

### Direção de streams
- *Input streams*
    >Classe base `InputStream`.
- *Output streams*
    >Classe base `OutputStream`.

## Leitura de arquivos com `InputStream`
A partir de uma instância de `File`, que será passada como argumento no construtor de um `FileInputStream`
que será atribuído à uma variável do tipo `InputStream`.

Nesse caso a leitura é realizada por meio de *byte oriented stream*, retornando bytes por meio de `InputStream.read()`.

*Lembrando que assim como outros recursos de IO, um `InputStream` deve ser fechado por meio do método close().*

**Obs.:** Utilizar *try-with-resources* como boa prática.

## Escrita de arquivos com `OutputStream`
Uma instância de `FileOutputStream` pode ser atribuída a uma variável `OutputStream`,
e por vez pode ser chamado o método `OutputStream.write()` que receberá os bytes a serem escritos ou
uma **sequência de bytes** (`byte[]`), **posição inicial** e **posição final** (quantidade de bytes lidos).

No caso de utilizar um **array de bytes** estamos utilizando um *buffer*, onde o tamanho do array
é o tamanho do buffer.

Esse tipo de operação é utilizada para minimizar as interações e chamadas ao sistema de arquivos,
permitido uma leitura e escrita de blocos de bytes.

## Character oriented streams
São streams baseados em caracteres.

Recomendados a serem utilizados com arquivos texto, já que resolvem/abstraem questões de codificação e caracteres
especiais.

Classes base `Reader` e `Writer`.

Lembrando que *character oriented* streams utilizam *byte oriented* streams internamente.

## Leitura de arquivos texto com `FileReader`
Uma instância de `FileReader` pode ser atibuída a uma variável `Reader`.

A leitura é realizada a partir de um *character oriented* stream, podendo ter o valor lido injetado em um **array de char** por meio de `Reader.read()`.

## Escrita de arquivos texto com `FileWriter`
Uma instância de `FileWriter` pode ser atribuída a uma variável `Writer`.

O bloco de texto pode ser escrito por meio de `Writer.write()`.

*`Writer` escreve seguidamente, mesmo a cada chamada de `write`, sendo necessário passar o caractere de escape `"\n"` para quebra de linha.*

## Leitura otimizada de arquivos texto com `BufferedReader`
`BufferedReader` é uma classe *wrapper* para um `FileReader`, que realiza a leitura de forma otimizada de um
arquivo de texto.

Com essa implementação é possível realizar a leitura de linha-a-linha do arquivo,
deixando a cargo da implementação a identificação e leitura das linhas.

Para fins de otimização de I/O, essa classe realizar o armazenamento de blocos
de dados em uma área de memória (*buffer*) para reduzir chamadas ao sistema de arquivos,
principalmente em arquivos grandes.

## Escrita otimizada de arquivos com `BufferedWriter`
`BufferedWriter` é uma classe wrapper para um `FileWriter, que realiza a escrita otimizada de um arquivo de texto,
gravando os blocos de dados conforme necessário.

Segue a mesma proposta que `BufferedReader` para otimização de I/O, armazenando blocos na memória visando redução de chamadas ao sistemas de arquivos.

## `System.in` e `Scanner` (Uso da API de I/O)
`System.in` é um `InputStream`.

`Scanner` é um objeto que consome os dados de um `InputStream`.

Para consumir os dados de `System.in` (que é um `InputStream`) em um `BufferedReader`,
necessitamos de um objeto wrapper `InputStreamReader` encapsulando o `InputStream`,
e sendo esse passado como parâmetro no construtor do `BufferedReader`.

Lembrando que esse é apenas um passo experimental, já que é provida a classe `Scanner` que abstrai esse trabalho.

## `System.out` e `PrintStream` (Uso da API de I/O)

`System.out` é um `PrintStream` (Que é um `OutputStream`).

Pode ser utilizando para saída de dados, no caso impressão de texto
para um stream de destino (`OutputStream), como o console ou um arquivo por exemplo.

----