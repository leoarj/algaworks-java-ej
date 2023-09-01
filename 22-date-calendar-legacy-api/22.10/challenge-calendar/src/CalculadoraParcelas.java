import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CalculadoraParcelas {

    /*
    * Simples exemplo de uso de algumas classes da api legada de Data e Hora do Java.
    */

    public void calcular() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Data da primeira parcela: ");
            String dataPrimeiraParcelaInput = scanner.nextLine();

            System.out.print("Quantidade de parcelas: ");
            int quantidadeParcelasInput = scanner.nextInt();

            DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPrimeiraParcela = formatador.parse(dataPrimeiraParcelaInput);

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(dataPrimeiraParcela);
            int diaPrimeiraParcela = calendar.get(Calendar.DAY_OF_MONTH);

            Date dataParcelaAtual = dataPrimeiraParcela;
            int quantidadeParcelasProcessadas = 0;

            do {
                System.out.printf("Parcela #%d - %s%n", ++quantidadeParcelasProcessadas, formatador.format(dataParcelaAtual));

                if (quantidadeParcelasProcessadas < quantidadeParcelasInput) {
                    calendar.add(Calendar.MONTH, 1);
                    int diaMaximoMesAtual = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                    if (diaPrimeiraParcela > diaMaximoMesAtual) {
                        calendar.set(Calendar.DAY_OF_MONTH, diaMaximoMesAtual);
                    } else {
                        calendar.set(Calendar.DAY_OF_MONTH, diaPrimeiraParcela);
                    }

                    dataParcelaAtual = calendar.getTime();
                }
            } while (quantidadeParcelasProcessadas < quantidadeParcelasInput);
        } catch (ParseException e) {
            System.err.printf("Erro ao calcular parcelas! Verifique os dados informados.%nCausa: %s", e.getMessage());
        }
    }
}