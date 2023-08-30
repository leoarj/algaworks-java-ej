import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class Cambio {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Preço do produto em Dólares: ");
            String precoDolaresInput = scanner.nextLine();

            System.out.print("Preço de 1 Dólar em Real: ");
            String cotacaoDolarInput = scanner.nextLine();

            DecimalFormat formatDolar = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.US));
            formatDolar.setParseBigDecimal(true);
            BigDecimal precoDolares = (BigDecimal) formatDolar.parse(precoDolaresInput);

            DecimalFormat formatReal = new DecimalFormat("#,##0.00",
                    new DecimalFormatSymbols(new Locale("pt", "BR")));
            formatReal.setParseBigDecimal(true);
            BigDecimal cotacaoDolar = (BigDecimal) formatReal.parse(cotacaoDolarInput);

            BigDecimal precoProduto = precoDolares.multiply(cotacaoDolar);

            formatReal.applyPattern("¤ #,##0.00");

            System.out.printf("Preço do Produto em Reais: %s", formatReal.format(precoProduto));
        } catch (ParseException e) {
            System.err.printf("Erro ao realizar cálculo de conversão. Verifique o valor informado!%nMotivo: %s%n", e.getMessage());
        }
    }
}