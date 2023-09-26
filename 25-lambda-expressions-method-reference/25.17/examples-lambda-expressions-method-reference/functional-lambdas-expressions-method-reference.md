# Expressões lambda e method reference.

## Expressões lambda = Funções anônimas, que podem ser passadas como argumento para outras funções.

"***Lambdas são funções anônimas que cumprem um contrato de uma interface funcional e podem ser passadas como argumento para composição de funções/execução de algoritmos de forma reaproveitável.***"

Ajudam a reduzir código boilerplate.

### Definição básica = `(parametros...) -> expressão(parametros...);`
- Parte da esquerda são os parâmetros da função.
- Separador `->` = **Arrow operator**
- Parte da direita = Implementação da função.

Exemplo: `product -> product.getValue()`

------------


#### "*Cidadãos de primeira classe da linguagem (First classe language citzen)*"

*Quer dizer que, funções podem ser passadas como argumento para outras funções como se fosse qualquer outro valor ou atribuídas a variáveis.*

*Fazem parte do conceito de Programação Funcional, um paragima declarativo, onde se declara o fluxo de processamento para se alcançar um resultado.*

------------


## Interfaces Funcionais:

### Interfaces com apenas um método "**SAM (Single Abstract Method)**".

Em Java existe a anotação `@FunctionalInterface` que marca uma interface como funcional e ajuda a prevenir que o código
seja quebrado por alterações indevidas, já que um aviso é exibido em **tempo que compilação**.

#### Uso de expressões lambda se dá com o uso de interfaces funcionais, ou seja:
- **Lambdas** = quando há apenas um método abstrato para ser realizado.
- **Classes anônimas** = No caso de haver mais de um método abstrato.

Sendo assim, interfaces funcionais não substituem clases anônimas em todos os casos, mas cada uma cumpre seu papel no devido contexto.

#### Com relação a escopos, temos as diferenças entre lambdas e classes anônimas:
- Lambdas = Não cria novo escopo (Trabalha com o escopo superior do método).
  Isso significa que não é possível declarar uma variável dentro da expressão e nem mesmo uma variável do contexto superior pode ser alterada dentro da expressão.
  A idéia é de **imutabilidade**, sendo que a expressão trata as variáveis do escopo superior como final.

- **Classes anônimas** = Cria novo escopo, sendo assim, é possível declarar uma nova variável até de mesmo nome que alguma que exista no escopo superior e alterar seu valor sem restrições, não impede a **mutabilidade**.

#### Boas práticas com lambdas:
- Tornar expressões mais concisas (Código mais curto);
- Uma única expressão não precisa de declaração de bloco `{}`;
- Não precisa de return, porque já é inferido conforme o tipo esperado;
- Até **3** linhas, se não for possível, criar método e chamar dentro da lambda expression;
- Evitar especificar o tipo dos parâmetros, porque também já é inferido, bastando apenas informar o nome do argumento.

#### Observações adicionais:
- Métodos implementados por `Object` não descaracterizam a interface como funcional, caso estes estejam declarados na mesma.

**Obs.:** Mais detalhes na classe de exemplos `LambdasExample` do projeto *examples-lambda-expressions-method-reference*.

## Interfaces funcionals padrão (java.util.function):

### 4 Principais interfaces funcionais:
- `Predicate<T>` = Testa um objeto retornando um valor booleano;
- `Function<T,R>` = Aplica uma transformação a partir de um objeto de entrada e fornece um resultado;
- `Supplier<T>` = Não recebe um argumento, mas implementa um retorno;
- `Consumer<T>` = Consume um valor mas não retorna nada (void);

**Obs.:** Há também as interfaces funcionais baseadas em tipos primitivos, como **IntPredicate**, **ToIntFunction** etc.
São úteis quando se precisa trabalhar com **tipos primitivos** e evitar **boxing/unboxing** com tipos **wrapper**.

#### Exemplos de uso:

- **Predicate:**
  Remoção de elementos de coleções, com o método `removeIf()` de `Collection`.

- ****Supplier****
  Adição de elementos em coleções através do método `add()`.
> A partir do fornecimento de um objeto qualquer, não importando parâmetros e detalhes de como vai ser obtido, como um ``DateTime.now`` encapsulado por exemplo.

- **Consumer:**
  Iteração de coleções com `forEach()`.

- **Function:**
  Ordenação de coleções em conjuto com `Comparator.comparing()`.

**Obs.:** É possível **compor** as funções, assim várias lógicas podem ser encadeadas declarativamente.

------------


## Method Referece

"***Method reference representa uma referência a um método de uma classe, seja ele da instância ou estático.
Caso a assinatura do método (argumentos ou retorno dependendo do caso) obedeça à especificação de uma interface funcional, então ele pode ser passado como argumento (Pode ser repassado como referência de interface funcional).***"

### Definição básica = `Classe::metodo`

> Tipo de classe | Nome do método sem parâmetros

**Exemplo:**
```java
Product::getProductList
```

> Argumentos e/ou retorno do método devem ter mesma classe do tipo da coleção/interface funcional.

É possível também referenciar métodos de uma instância particular (Método de outra classe que não a mesma da tipagem da interface funcional ou coleção).
> "Deve-se criar uma instância e repassar referenciando o método a partir dela."
`variavelDeOutraClasse::metodo`

> "Método da outra classe deve receber um argumento de mesmo tipo da classe parametrizada na interface funcional."

É possível também referenciar métodos estáticos.
> "Possuir argumentos de mesmo tipo."
`Classe::metodoEstatico`

**Obs.:** Mais detalhes na classe de exemplos `MethodReferenceExample` do projeto *examples-lambda-expressions-method-reference*.

------------

## Constructor Reference

"***São referências ao construtor de uma classe, onde a assinatura do SAM (Single Abstract Method) dever ter argumentos correspondentes com os argumentos do construtor da classe para que possa ser utilizado.***"

### Definição básica = `Classe::new`

Com o uso de Constructor Reference, podemos substituir a chamada direta ao construtor de uma classe optando por receber
um argumento do tipo de uma **Function** ou outra interface funcional que realize a transformação de valores informados
em objetos da classe desejada.

Ou seja, em primariamente uma Function ou outra interface funcional que realize a transformação de um valor em outro são as interfaces
corretas para se referenciar construtores.

**Obs.:** Mais detalhes na classe de exemplos `ConstructorsReferenceExample` do projeto *examples-lambda-expressions-method-reference*.

------------



