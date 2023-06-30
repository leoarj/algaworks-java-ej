package composition;

public class ContratoTrabalho {

    Funcionario funcionario;
    double valorHoraNormal;
    double valorHoraExtra;

    boolean possuiAdicionalFilhos() {
        return funcionario.possuiFilhos();
    }
}
