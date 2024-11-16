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

    private String nome;
    private LocalDate dataNascimento;
    private BigDecimal salario;

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