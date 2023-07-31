Generics é um recurso que foi introduzido no Java 5.

Também conhecido como polimorfismo paramétrico.

É poderoso e fornece a possbilidade de se definir classes, interfaces e métodos
com definições genéricas, que podem ser realizados no momento da instanciação da classe com tipo parametrizado
ou por classes concretas específicas.

Generics oferece segurança de tratamentos de tipos em tempo de compilação (compile-time feature).

Reduzir casts arriscados, ajudando a evitar ClassCastException é uma das principais
características de Generics (Reduzir erros em runtime).

Outro principal fator é o reaproveitamento (código resusável) de código e melhoria na manutenibilidade,
podendo se alcancar um código mais flexível.

* Temos duas formas de difinir tipos em Generics no Java (Convenções):
- 1 - Utilizando Type Parameters, onde se sabe sabe o tipo no momento da instanciação/realização:

- E = (Element) Representa algum tipo de um elemento (Usado em grande parte no Collections Framework);
- K = (Key) Representa algum tipo de uma chave de um Map por exemplo.
- N = (Number) Representa algum tipo com relação a numéricos.
- T = (Type) Representa algum tipo que virá a ser conhecido.
- V = (Value) Representa algum tipo do valor em um Map por exemplo.
- S, U, V etc = Representam demais tipos e assim por diante.

Obs.: Essa é um convenção, porém outras abreviações, com mais de uma letra
inclusive, podem ser utilizadas para definir tipos parametrizados.

Fonte: https://docs.oracle.com/javase/tutorial/java/generics/types.html

* 2 - Utilizando Wildcards (curingas):
- ? = Representa qualquer tipo (anônimo) (Quando não delimitado, estamos praticamente
trabalhando com Object).

Obs.: Muito úteis quando se quer adicionar um novo método genérico,
mas não quer ou não pode modificar a classe (parâmetros de tipo da classe).

* Delimitação de tipos genéricos:
- unbounded -> não delimitado
- lower bounded (super) -> delimitação inferior (igual ou ""menor que tipo""), baixamente delimitado.
- upper bounded (extends) -> delimitação superior (igual ou ""maior que tipo""), altamente delimitado.

* As delimitações definem a variância:
- Covariancia (Covariance) = <T extends> ou <? extends> -> Somente o tipo e seus subtipos são aceitos no contexto.
- Contravariancia (Contravariance) = <? super> -> Somente o tipo e seus supertipos são aceitos no contexto.
- Invariancia (Invariance) = <T> -> Somente o tipo é aceito no contexto.
- Obs.: super só pode ser utilizado com o operador wildcard.

* Diferença de T (Type Parameter) para ? (Wildcard) quando são unbounded:
- T unbounded = Estritamente aquele tipo definido que será aceito (invariante).
- ? unbounded = Qualquer tipo será aceito.

* PECS = Producer -> extends, Consumer -> super
- Utilizamos <T extends> ou <? extends> (covariante) quando queremos recuperar elementos de uma estrutura.
- Utilizamos <? super> (contravariante) quando queremos modificar/adicionar elementos em uma estrutura.
- Utilizamos <T> (invariante) quando queremos recuperar e/ou modificar elementos de uma estrutura.
- Utilizamos <?> (independente) quando não precisamos consumir/produzir elementos de uma estrutura.

O uso de Generics é vasto na plataforme Java, principalmente no Collections Framework.
Obs.: Coleções em Java são naturalmente invariantes.
Arrays são naturalmente co-variantes.

Obs.: Esse exercício não faz parte do curso.
Foi um estudo extra, com outros materiais para reforçar os tópicos de generics em Java.

