#Java Logging API, Logback e SLF4J

## Níveis de Logging da *JUL*
- SEVERE (highest value)
- WARNING
- INFO
- CONFIG
- FINE
- FINER
- FINEST (lowest value)

### `logging.properties`
VM Options
-Djava.util.logging.config.file=logging.properties

## Logback com SLF4J
- Remover configurações de VM Options para o JUL
- Excluir arquivos `logging.properties` e `banco-app.log`
- Adicionar dependências do Logback

### Níveis de Logging do Logback e SLF4J
- TRACE
    >Mais granular, mensagens mais detalhadas (geralmente em ambiente de desenvolvimento e teste)
- DEBUG
    >Mais granular, detalhes de depuração (geralmente em ambiente de desenvolvimento e teste)
- INFO
    >Informacional (não são erros ou warnings), mas quando uma ação aconteceu por exemplo
- WARN
    >Aviso, algum evento que pode se tornar um problema futuramente
- ERROR
    >Informação crítica de erro

- Refs.:
    - https://www.slf4j.org/docs.html
    - https://logback.qos.ch/documentation.html

### Configuração da sáida de logs `logback.xml`