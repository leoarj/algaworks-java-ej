# Streams API `java.util.stream`

"***Operações sobre coleções, de forma funcional (declarativa).***"

"***Operações em massa sobre uma sequência de elementos.***"

Streams, podem ser definidos com uma sequência de elementos, obtidos a partir de uma fonte de dados.
Introduzida a partir do Java 8.

**Observações:**
- Não duplica os elementos/coleção, é uma forma de processar elementos da fonte de dados.

------------


## Pacote: `java.util.stream`
Conjunto de interfaces e classes que provêm suporte a operações no estilo funcional, que podem ser aplicados em uma forma de fluxo (pipeline) sobre elementos de forma a se obter um resultado.

------------

## Operações "intermediárias" (filter() por exemplo):
Operações intermediárias não executam o fluxo, mas definem o processamento do fluxo, onde a execução de dará de fato ao chamar uma operação "terminal".

O método `Filter()` define por exemplo uma função intermediária, onde ela pode ser definida no processamento, indicando as condições de execução, porém sua execução só de dará de fato com a chamada de uma operação terminal no final do fluxo.

### Laziness operations:
Definem operações "tardias" que são declaradas no fluxo (condições de processamento), mas por otimização são executadas sob demanda, ao uma função terminal chamar todo o fluxo, esse é o caso das funções intemediárias.

------------

## Operações "terminais" (forEach() por exemplo):
Definem operações no final do fluxo, que obrigatoriamente levarão à execução da cadeia de declarações estabelecidas na pipeline.

O método `forEach()` define por exemplo uma função terminal, onde ela é definida no final do fluxo e executada, levando a execução das definições anteriores do fluxo composto por funções intermediárias.
O método `forEach()` não tem retorno (void), desse modo não é possível mais encadear outras chamadas, firmando assim o conceito de função terminal.

------------

## Definição básica:
- Stream = fonte de dados -> operações intermediárias -> operação terminal.

------------

## "Execução" de funções terminais entre funções intermediárias
### Método `peek()`:
Age com uma função intermediária porém aceita um Consumer que será executado na cadeia de chamadas do fluxo, dessa forma sendo possível executar ações entre as definições (condições) do stream.
Em resumo, é como se fosse possível executar uma função terminal entre funções intermediárias.

Obs.: Geralmente utilizada para depuração da execução da pipeline.

------------
## Operações de curto-circuito
São operações que "quebram" o fluxo de execução retornando Optional com um resultado que atenda a descrição do fluxo.

### Métodos que atendem ao conceito:
- `findFirst()` = Retorna um Optional do primeiro elemento do stream.
- `findAny()` = Retorna um Optional de qualquer elemento do stream.

------------



## Operações de curto-circuito com teste de predicados
São funções que retornam um valor booleano indicando se qualquer, todos ou nenhum elemento atendem as definições do fluxo.

### Exemplo de métodos da Stream API:
- `Stream.anyMatch()`
> Retorna true se pelo menos 01 elemento (qualquer) atende à definição do fluxo.

- `Stream.allMatch()`
> Retorna true se todos os elementos atendem à definição do fluxo.

- `Stream.noneMatch()`
> Retorna true se nenhum elemento atendem à definição do fluxo (negação).

------------



## Ordenação de elementos
Para ordernar um stream, podemos utilizar os métodos a segui:
- `sorted()`
> Realiza a ordenação natural, pela implementação de Comparable fornecida na classe do elemento.

- `sorted(Comparator<? super T> comparator)`
> Realiza a ordenação a partir de uma comparação  diferente. Podendo utilizar `Comparator.comparing()` para realizar a ordenação diferente.

**Obs.:** Não está alterando a ordem da fonte de dados, apenas do stream.

------------



## Operação intermediária com estado (statefull)
São operações que guardam estado, sendo que necessitam executar sobre todos os elementos para que se alcançe o resultado final, transformando o stream.

### Exemplos:
- `sorted()` e - `sorted(Comparator<? super T> comparator)`
> Porque precisam "passar" por todos os elementos para que a ordenação seja concluída.

- `distinct()`
> Porque precisa passar por todos os elementos para agrupar/remover duplicados e apresentar apenas uma ocorrência de cada especificação de elemento.

------------



## Transformações com `Stream.map()`
É possível obter um stream de outro tipo de dado a partir de transformações através de uma function passada para o método `map(Function<? super T,? extends R> mapper)`.

Ou seja, dessa forma podemos obter elementos que estão contidos dentro do elemento fonte e continuar processando um stream do tipo do elemento mapeado.

## Obtendo elementos distintos
Para se agrupar/reduzir duplicados, de forma a apresentar apenas 01 ocorrência de cada elemento da definição.

### Exemplo de métodos na Stream API:
- `distinct() `

#### Observações:
- É uma operação **statefull**.
- É necessário que o método `equals()` esteja implementado na classe dos elementos do stream.

------------



## "Achatando" stream com `flatMap()`

Retorna diretamente o retorno fornecido através da function de transformação em vez de um stream do tipo fornecido.

### Quando utilizar:
- Quando temos uma situação onde surgirá um Stream de um Stream e sucessivamente.
- Quando objeto conter uma collection e termos que retornar um stream da collection para continuar a execução do fluxo.

------------

## Stream para tipos primitivos

São tipos de stream especiais para se trabalhar com tipos primitivos quando necessário,
evitar boxing/unboxing de tipos wrapper e se aproveitar de métodos de conveniência como `sum()`
que já fornece um algoritmo soma em um stream de inteiros primitivos.

Para tipos primitivos tempos:
- `IntStream` = Stream para inteiros na faixa de valores de `int`.
- `LongStream` = Stream para inteiros na faixa de valores de `long`.
- `DoubleStream` = Stream para valores com casas decimais na faixa de valores de `double`.

------------


## Operações de redução (reduce)

Operações de redução são operações terminais que acumulam um resultado final
a partir de um valor inicial (indentity) e uma lógica de acumulação, que pode ser:
- soma
- multiplicação
- divisão
- subtração
- outros cálculos.

A lógica de acumulação é processada em cadeia a partir dos elementos do stream
e o resultado de acordo com o tipo do stream, seja ele mapeado (transformado) anteriormente ou não.

### Redução para outros tipos do stream e funções de combinação

É possível reduzir elementos em um resultado de outro tipo, como `BigDecimal` por exemplo.

É necessário informar dois argumentos para a função `reduce` caso o stream original seja **mapeado para o tipo do resultado**:
- `T identity`
> Valor inicial da redução.

- ` java.util.function.BinaryOperator<T> accumulator`
> Função para a lógica de como os valores serão acumulados.

Caso **não** seja feito o mapeamento para o tipo do resultado e desejarmos obter a redução diretamente do stream original, se faz necessário informar um argumento adicional:
- ` java.util.function.BinaryOperator<U> combiner`
> Combinador de resultados  para dois elementos, como eles vão sendo acumulados no resultado final e também como resultados processados em separado (streams paralelos) terão seus resultados combinados no resultado final.

### Reduce com Optional

São operações que podem necessariamente não retornar um valor do processamento do fluxo, então assim um retorno de optional se faz necessário.

Exemplos são operalções com streams especiais para tipos primitivos:

- sum()
> Somatória de elementos.

- average()
> Média de elementos.

- count()
> Ocorrência de elementos.

- min()
> Menor valor de elemento.

- max()
> Máximo valor de elemento.

------------


## Collect (Coletores em streams)

`collect()` é uma operação terminal de redução, onde elementos de um stream são coletados
para uma coleção.

`collect()` recebe 3 argumentos quando não utilizados os coletores padrão:

- `Supplier<R> supplier`
> Para qual tipo de contêiner que vai receber os elementos.

- `BiConsumer<R,? super T> accumulator`
> Como os elementos vão ser adicionados na coleção.

- `BiConsumer<R,R> combiner`
> Combinador para processamento paralelo e lógica de como combinar as coleções (`addAll()` por exemplo).

## Classe `Collectors` (Coletores padrão)

Classe que fornecem coletores padrão com algoritmos para se poder coletar os
elementos para coleções específicas.

- `Collectors.toList()`
> Coleta os elementos do stream para uma List.
Obs.: Não há garantia de qual implementação de List o coletor vai retornar.

- Collectors.toCollection(Supplier de uma coleção)
> Coleta os elementos para uma coleção específica provida através de uma função de supplier.

### Coletores para listas não modificáveis

Funções para coletar os resultados para uma coleção não modificável (somente-leitura).
Útil quando apenas queremos consumir os elementos e não modifica a coleção obtida.

Exemplos:
- `Collectors.toUnmodifiableList() `
> Coleta para uma lista não-modificável.

`Stream.toList()`
> Coleta direto do stream para uma lista não-modificável, sem ter que chamar o método `collect()`.

### Coletando elementos em mapas

É uma forma de agrupamento de elementos por uma definição de chave única em um map.

`Colectors.toMap()` pode ser utilizado para isso, onde o tipo do valor é do tipo dos elementos do stream:
- `Function<? super T,? extends K> keyMapper`
> Function para mapear a chave.

- `Function<? super T,? extends U> valueMapper`
- Function para mapear o valor.

### Coletando em mapas com agrupamento (`Collectors.groupingBy()`)

É possível gerar um `Map` (chave/collection) a partir de um critério de agrupamento,
acumulando os valores em uma collection que será usada como valor de cada chave do `Map`.

Para isso o método `groupingBy()` de `Collectors` pode ser usado.

### Agrupamento com valores agregados (Redução mapeada)

É possível gerarmos um `Map` com valores agregagos, a partir de uma redução dos elementos por chave do `Map`, que é definida conforme um critério único (classifier).

A redução/acumulação do resultado se dá conforme um coletor (downstream) no que será o valor agrupado para cada chave.

Sendo assim, temos um Map com valores acumulados para cada chave.

**donwstream** = Em direção ao fluxo, forma como os resultados serão combinados.

Alguns coletores agregados padrão:
- `Collectors.counting()`
> Realiza a contagem de ocorrências de elementos para cada chave. Realiza a contagem de ocorrências de elementos para cada chave.

- `Collectors.summingInt()`
> Realiza a somatória de um valor inteiro fornecido por elemento e sendo acumulado por chave. Realiza a somatória de um valor inteiro fornecido por elemento e sendo acumulado por chave.

## Mapas particionados

`Collectors.partitioningBy()`

Podemos particionar os elementos em duas partes (true/false) em um Map com chave do tipo Boolean,
conforme um predicado de separação, onde os elementos são agrupados em uma List para verdadeiro e outra List para falso.


------------


## Obtendo streams de outras fontes
Métodos que podem ser utilizados para se obter streams a partir de outras fontes:
- `Arrays.stream()`
  -`Stream.iterate()` (infinite strean)
- `Stream.of()`
- `Stream.concat()`
- `IntStream.range()`/`rangeClosed()`
- `Stream.generate()` (infinite stream)

**Obs.:** limit() serve para limitar o tamanho do stream.
**infinite stream** = Operação que gera um stream com processamento infinido, sem curto-circuito.
Nesse caso se utiliza o `limit()` para criar um limite de parada.

------------



## Objects.isNull() e Objects.noNull()
São métodos que encapsulam a lógica de verificação de `null`, são mais utilizados para serem passados como method reference em streams.

------------


## Boas práticas

- Evitar alteração de uma variável externa.

- `forEach()`
> De preferência apenas apresentar o resultado, não alterar o estado dos elementos.

- **"função pura"** -> Depende apenas da sua entrada e nenhum outro fator externo.
> Sempre tornar seus métodos em funções puras, para evitar side-effects.
Em resumo, sempre que possível evitar código imperativo em streams.

------------


