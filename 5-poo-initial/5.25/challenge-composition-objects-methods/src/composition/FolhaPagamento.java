package composition;

public class FolhaPagamento {

    Holerite calcularSalario(final ContratoTrabalho contratoTrabalho, final int quantidadeHorasNormaisTrabalhadas,
                             final int quantidadeHorasExtrasTrabalhadas) {

        Holerite holerite = new Holerite();

        holerite.funcionario = contratoTrabalho.funcionario;
        holerite.valorTotalHorasNormais = contratoTrabalho.valorHoraNormal * quantidadeHorasNormaisTrabalhadas;
        holerite.valorTotalHorasExtras = contratoTrabalho.valorHoraExtra * quantidadeHorasExtrasTrabalhadas;

        if (contratoTrabalho.possuiAdicionalFilhos()) {
            holerite.valorAdicionalFilhos = (holerite.valorTotalHorasNormais + holerite.valorTotalHorasExtras) * 0.1;
        }

        return holerite;
    }
}
