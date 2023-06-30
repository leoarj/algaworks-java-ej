/*
 * Records foi um recurso introduzido no Java 14.
 *
 * Pode ser útil para ser utilizado como uma "classe de dados" ou "value objects",
 * onde a classe atua apenas como uma estrutura de transporte de dados.
 * Dessa forma sua maior utilidade é resolver a passagem de dados imutáveis.
 *
 * Os Records já declaram seus atributos como final e os inicializa em um construtor default.
 * Na declaração de um record, deve se especificar seus atributos já no início,
 * e caso for sobreescrever o contrutor utilizando os atributos/argumentos (record components),
 * devem ser passados na mesma ordem de tipo e nome que foram declarados no início.
 */
public record Horario(int hora, int minuto) {

    /*
     * Caso exista a necessidade de apenas incluir lógicas e validação no construtor,
     * sem utilizar os atributos na declaração (já que os mesmos são inicializados automaticamente),
     * então a declaração segue apenas o padrão "public nomeRecord" (sem os parênteses).
     */
    public Horario {
        if (hora < 0 || hora > 23) {
            throw new IllegalArgumentException("Hora inválida: " + hora);
        }
        if (minuto < 0 || minuto > 59) {
            throw new IllegalArgumentException("Minuto inválido: " + minuto);
        }
    }

    /*
    * Sobreescrever o contrutor modificando a quantidade de argumentos é possível,
    * porém deve se delegar a chamada a outro contrutor, utilizando se a palavra reservada this,
    * assim como é com classes comuns.
    */
    public Horario(int hora) {
        this(hora, 0);
    }

    /*
     * Records podem ter métodos normalmente, mas não podem alterar as variáveis de instância.
     *
     * Records não possuem setters e o acesso aos atributos externamente se dá por meio de métodos
     * gerados automaticamente sem a nomenclatura get dos JavaB Beans,
     * mas seguem o próprio nome do atributo acompanhado de () parênteses.
     * Ex.: hora se torna hora();
     */
    public String formatar() {
        return String.format("%dh%dm", hora(), minuto());
    }
}
