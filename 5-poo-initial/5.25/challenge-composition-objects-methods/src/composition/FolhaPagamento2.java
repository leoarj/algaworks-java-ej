package composition;

public class FolhaPagamento2 {

    Holerite calcularSalario(final CalcularSalarioContexto contexto) {

        Holerite holerite = new Holerite();

        holerite.funcionario = contexto.contratoTrabalho().funcionario;
        holerite.valorTotalHorasNormais = contexto.contratoTrabalho().valorHoraNormal * contexto.quantidadeHorasNormaisTrabalhadas();
        holerite.valorTotalHorasExtras = contexto.contratoTrabalho().valorHoraExtra * contexto.quantidadeHorasExtrasTrabalhadas();

        if (contexto.contratoTrabalho().possuiAdicionalFilhos()) {
            holerite.valorAdicionalFilhos = (holerite.valorTotalHorasNormais + holerite.valorTotalHorasExtras) * 0.1;
        }

        return holerite;
    }
}
