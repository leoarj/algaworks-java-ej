import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * - Serialização = Processo de tornar um objeto instanciado na memória heap
 * e transformá-lo em um fluxo de bytes.
 * - Desserialização = Processo de contruir um objeto a partir de um fluxo de bytes,
 * de um objeto que já tenha sido serializado antes.
 *
 * Para que um objeto possa ser serializado/desserializado,
 * ele deve implementar a interface de marcação {@link Serializable}.
 */
public class Funcionario implements Serializable {

    /*
    * Para controlar versão da estrutura da classe referente
    * a compatibilidade de serialização/desserialização.
    *
    * Anotação @Serial do pacote java.io ajuda a verificar em tempo de compilação
    * elementos que fazem parte da estrutura de serialização.
    *
    * Caso as versões de serialização entre o objeto serializado
    * e a estrutura da classe atual sejam diferentes,
    * então houve quebra de compatibilidade, com uma java.io.InvalidClassException sendo lançada.
    *
    * Boas práticas:
    * - Configurar na IDE para lembrar de gerar o serialVersionUID
    * caso a classe implemente Serializable.
    * - Implementar Serializable em classes apenas que sejam necessárias,
    * porque isso reduz a flexibilidade de alteração da classe.
    *
    * Obs.: O cálculo do valor do serialVersionUID se baseia na estrutura da classe
    * (nome, interfaces que implementa, propriedades, métodos etc.).
    */
    @Serial
    private static final long serialVersionUID = -5578834679700950299L;


    private String nome;
    private LocalDate dataNascimento;
    /*
    * Com a palavra chave "transient",
    * uma propriedade por ser marcada para não ser serializada/desserializada.
    * Útil para informações sensíveis, informações calculadas etc.
    */
    private transient BigDecimal salario;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", salario=" + salario +
                '}';
    }

}