# Importando arquivos JAR no projeto
- No projeto que vai usar a biblioteca
  - Criar a pasta `lib`
    - Copiar .jar para a pasta `lib`
    - No IntelliJ, no projeto, acessar:
      - Menu Open Module Settings -> Libraries
        - Adicionar a biblioteca presente na pasta `lib`
        
## Usando bibliotecas de terceiros
- Exemplo com Apache Commons Lang
  - Site: https://commons.apache.org/proper/commons-lang/
  - Artefato utilizado: commons-lang3-3.17.0.jar
  - Realizar os mesmos passos anteriores para cópia/inclusão no projeto
  - Exemplo de classe utilizada do pacote: `StringUtils`

### Observações
- O projeto aumenta de tamanho em relação ao espaço em disco.
- Em tempo de execução, apenas o que é utilizado é carregado.
  >Outras classes das bibliotecas não são carregas ou impactam o desempenho da aplicação.

## Gerando JARs executáveis
### Gerando no IntelliJ IDEA
- Menu (botão direito no projeto)
- -> Open Module Settings
  - -> Artifacts -> JAR -> from modules with dependencies
  - -> Apontar qual a classe main (entry-point) do projeto

### JARs de bibliotecas e inclusão ou linkagem

#### META-INF/MANIFEST.MF
Arquivo de "manifesto".

Ele carrega metainformações para o arquivo jar como:
- Main-Class
  >Classe principal, a ser executada.
- Class-Path
  >Referência as dependências, fazendo o link com elas.

#### Dependências no artefato final
- *extract to the target JAR*
  >Extrai o conteúdo das dependências para dentro do jar final.
- *copy to the output directory and link via manifest*
  >Copia os JARs das dependências na mesma pasta que o artefato final,
  > e realiza o link via `MANIFEST.MF`.
