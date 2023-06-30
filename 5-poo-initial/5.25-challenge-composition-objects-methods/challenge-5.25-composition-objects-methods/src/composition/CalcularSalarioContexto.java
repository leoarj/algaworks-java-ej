package composition;

public record CalcularSalarioContexto(ContratoTrabalho contratoTrabalho,
                                      int quantidadeHorasNormaisTrabalhadas,
                                      int quantidadeHorasExtrasTrabalhadas) {
}