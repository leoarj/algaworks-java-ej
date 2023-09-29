# Optional (java.util.Optional)

## Optional`<T>` = Classe para criar containers de objeto, que podem conter ou não um valor não-nulo.

"***A classe `java.util.Optional` é a forma de tratar retornos de métodos que podem ou não retornar um valor, nesse caso para evitar o retorno de `null` e evitar erros de `NullPointerException`.***

***Com `Optional` temos a possibilidade de testar se um valor está "presente" antes de consumi-lo, dando abertura ao método que retorna o valor a possibilidade de em vez de lançar uma exception, retornar um Optional nos casos onde o valor pode ser nulo ou não.***

***Em casos onde obrigatoriamente um valor direferente de nulo deve ser retornado, então a abordagem de lançar exception continua a mesma, porém pode-se aproveitar do lançamento de `NoSuchElementException` através `Optional.of()`.***"

### Definição básica = `Optional<T>`
- Optional = Classe container para objetos.
- Tipo parametrizado que uma instância de Optional deve comportar.

------------

## Métodos mais usados

### - `isPresent()`
> Retorna true caso o objeto contido no optional não seja nulo e false caso seja.

### - `orElseThrow(Supplier<? extends X> exceptionSupplier)`
> Retorna o valor contido no optional porém lança uma exception caso o valor seja nulo.
Obs.: A exception a ser lançada é fornercida por meio de um Supplier.

### - `orElseGet(Supplier<? extends T> supplier)`
> Retorna o valor contido no optional ou retorna um outro valor obtido a partir de um Supplier e retorna o novo valor.

### - `ifPresent(Consumer<? super T> action)`
> Executa uma ação a partir de um consumer caso o valor esteja presente.

### - `ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)`
> Executa uma ação a partir de um consumer caso o valor esteja presente ou uma outra ação sem receber parâmetros e sem retorno caso contrário.

### - `filter(Predicate<? super T> predicate)`
> Aplica uma condição para retornar o valor/valores contidos no encadeamento do chamadas no optional.

### - `map(Function<? super T,? extends U> mapper)`
> Faz uma transformação a partir de um valor e retorna um optional contendo o valor transformado.

### - `flatMap(Function<? super T,? extends Optional<? extends U>> mapper)`
> Faz uma transformação a partir de um valor retornando o valor "achatado" (sem estar embutido em um optional), em resumo retorna o dito valor transformado e não um optional contendo o valor transformado.

------------

## Optional para tipos primitivos

Existem classes de optional projetadas para tipos primitivos e são úteis quando querermos aproveitar os recursos de optional mas não queremos utilizar classes wrapper e correr o risco do boxing/unboxing degradar performance em grandes processamentos de dados.

### - `OptionalInt`
> Optional para tipo primitivo `int`.

### - `OptionalInt`
> Optional para tipo primitivo `long`.

### - `OptionalDouble`
> Optional para tipo primitivo `double`.

------------

## Boas práticas com Optional

### - Não utilizar Optional em tudo:
#### - atributos de classe (deixar null caso não inicializado)
#### - paramêtros e construtores (traz complexidade no consumo dos argumentos)
#### - coleções (adicionar diretamente o valor e não um optional dele, para não alocar mais objetos desnecessariamente)

### Onde e quando utilizar:
#### - Ser utilizada no retorno de métodos, que podem ter ou não o valor.
#### - Se método não retorna `Optional`, basta testar `null` mesmo, em vez de criar um optional apenas para chamar `isPresent()`.

Optional são muito úteis, porém deve se atender que há um custo de utilizar e deve se contextualizar quando aplicar o seu uso.

------------

