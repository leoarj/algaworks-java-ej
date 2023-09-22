import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Principal {

	private static final DecimalFormatSymbols FORMAT_SYMBOLS_PT_BR = new DecimalFormatSymbols(new Locale("pt", "BR"));
	private static final DecimalFormat FORMATADOR_VALOR = new DecimalFormat("¤ #,##0.00", FORMAT_SYMBOLS_PT_BR);
	private static final DecimalFormat FORMATADOR_PERCENTUAL = new DecimalFormat("#,##0.00%", FORMAT_SYMBOLS_PT_BR);
	private static final BigDecimal PERCENTUAL_IMPOSTOS = new BigDecimal("0.2");

    public static void main(String[] args) {
		var funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(new Funcionario("João", new BigDecimal("19000"), true));
		funcionarios.add(new Funcionario("Maria", new BigDecimal("5000"), true));
		funcionarios.add(new Funcionario("Manoel", new BigDecimal("13000"), true));
		funcionarios.add(new Funcionario("Sebastião", new BigDecimal("12000"), false));

		// DONE remover funcionários inativos
		Predicate<Funcionario> funcionarioPredicate = funcionario -> funcionario.isInativo();
		funcionarios.removeIf(funcionarioPredicate);

		// DONE ordenar funcionários pelo valor do salário
		Function<Funcionario, BigDecimal> funcionarioFunction = funcionario -> funcionario.getSalario();
		funcionarios.sort(Comparator.comparing(funcionarioFunction));

		// DONE iterar e imprimir funcionários usando o método estático imprimir
		Consumer<Funcionario> funcionarioConsumer = funcionario -> imprimir(funcionario);
		funcionarios.forEach(funcionarioConsumer);
	}

	private static void imprimir(Funcionario funcionario) {
		// DONE implementar a impressão do nome, salário e impostos (20%)
		System.out.printf("Nome: %s, Salário %s, Impostos: %s (%s)%n",
				funcionario.getNome(),
				FORMATADOR_VALOR.format(funcionario.getSalario()),
				FORMATADOR_VALOR.format(funcionario.getSalario().multiply(PERCENTUAL_IMPOSTOS)),
				FORMATADOR_PERCENTUAL.format(PERCENTUAL_IMPOSTOS));
	}
}