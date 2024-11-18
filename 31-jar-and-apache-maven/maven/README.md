# Apache Maven

## Instalando no MacOS e Linux
### Através do SDKMan
- Listar versões disponíveis
    ```console
    sdk list maven
    ```
- Instalar uma versão
    ```console
    sdk install maven 3.9.9
    ```

## Criar projeto Apache Maven
- ***GroudId**
    >Identifica o projeto, de preferência separado por área.
- ***ArtifactId***
    >Indentifica unicamente o artefato dentro do grupo.

## `pom.xml` *(Project Object Model)*
O `pom.xml` é o arquivo de definição de um projeto que utiliza a estrutura do Apache Maven.

Nele são controladas informações referentes ao projeto e controle de dependências.

### Principais tags/propriedades
- `<modelVersion>4.0.0</modelVersion>`
    >Versão da estrutura do `pom.xml`.

#### Maven Coordinates
- Usado para identificar artefatos (partes do sofware)
    - Exemplo
        ```xml
        <!-- Grupo de projeto -->
        <groupId>com.algaworks.crm</groupId>
        <!-- Artefato do grupo de projeto -->
        <artifactId>crm-api</artifactId>
        <!-- 
        Versão do artefato.
        Maven utiliza versionamento semântico.
        SNAPSHOT - Indica que é uma versão em desenvolvimento.
        -->
        <version>1.0-SNAPSHOT</version>
        ```
    >As *Coordinates* são usadas para além disso, identificar e localizar um artefato em um repositório.

- Refs.:
    - https://maven.apache.org/pom.html#Maven_Coordinates

#### `<properties>`
- Exemplo
    ```xml
    <properties>
        <!-- Versão código-fonte Java que o compilador deve usar -->
        <maven.compiler.source>17</maven.compiler.source>
        <!-- Versão bytecode Java que será gerada -->
        <maven.compiler.target>17</maven.compiler.target>
        <!-- Define a codificação de caracteres que deve ser utilizada nos arquivos do projeto -->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    ```
## *Standard Directory Layout*

Estrutura padrão de diretório de projetos *Maven*

- `src`
    - `main`
        >Código principal, produção, funcionalidades do projeto.
        - `java`
            >Código-fonte Java.
        - `resources`
            >Arquivos de configurações, imagens e outros recursos.
    - `test`
        >Código de teste
        - `java`
            >Testes unitários, de integração, de API.
        - `resources`
            >Mesma ideia que o `resources` da `main`.

## Compilação e empacotamento

A compilação e empacotamento de projetos com o *Maven*, se dá por meio de **fases (phases)**, **plugins** e **goals**.

- Principais fases:
    - `mvn compile`
        >Compila o código-fonte do projeto.
    - `mvn clean`
        >Limpa arquivos `.class` e artefatos gerados.
    - `mvn package`
        >Realiza o empacotamento conforme o tipo (`<packaging>`) definido no `pom.xml` (padrão é **jar**).
    - `mvn install`
        >Instala um pacote no repositório local.
>As *phases* estão relacionadas com *plugins*.

- Principais plugins
    - clean
    - compiler
    - deploy
    - install
    - jar
    - resources
    - site
    - surefire
>Funcionalidades do Maven, são estendidas por meio de plugins.<br>
*Built-in* = plugins nativos.

>Cada plugin tem um ***goal*** (objetivo).

## Tipos de repositórios
- Remoto
    - Central (padrão do Maven)
- Local (cache)
    - subpasta `.m2`

## Fat JAR com Maven Assembly Plugin
https://maven.apache.org/plugins/maven-assembly-plugin/

