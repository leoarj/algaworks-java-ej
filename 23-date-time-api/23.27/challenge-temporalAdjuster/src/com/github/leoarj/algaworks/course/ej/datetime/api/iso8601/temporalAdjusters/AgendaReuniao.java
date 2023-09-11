package com.github.leoarj.algaworks.course.ej.datetime.api.iso8601.temporalAdjusters;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/*
* A API de date-time do Java provê algumas representações menores para objetos temporais,
* nas situações em que não se necessita de uma data completa.
*
* - Year = Representação ISO8601 de um ano.
* - Month = Representação ISO8601 de um mês do ano.
* - YearMonth = Representação ISO8601 de um Mês-Ano.
*
* São providos também TemporalAdjuster e TemporalAdjusters:
*
* - TemporalAdjuster = interface para que possa se implementar algoritmos
* de manipulação de objetos temporais, passado como argumento para o método with() de um objeto temporal.
* - TemporalAdjusters: Implementações padrão disponíveis para uso em boa parte das situações.
* */

public class AgendaReuniao {

    // Pattern do formatador com base em: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html
    private final static String DATA_REUNIAO_FORMATO_IMPRESSAO = "EEEE, d 'de' MMMM 'de' yyyy";

    public static void main(String[] args) {
        AgendaReuniao agendaReuniao = new AgendaReuniao();
        agendaReuniao.gerarAgendaAnual(DATA_REUNIAO_FORMATO_IMPRESSAO);
    }

    public void gerarAgendaAnual(String dataReuniaoFormatoImpressao) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Requisitos solicitados para implementação: Usar Year, Month, YearMonth.
            Year anoReuniao = solicitarAnoReuniao(scanner);
            Month mesInicial = solicitarMesInicial(scanner);
            YearMonth anoMesReuniao = anoReuniao.atMonth(mesInicial);

            System.out.printf("Gerando agenda anual de reuniões desde %s%n",
                    obterFormatadorComLocalePtBR("MMMM 'de' yyyy").format(anoMesReuniao));

            DateTimeFormatter formatador = obterFormatadorComLocalePtBR(dataReuniaoFormatoImpressao);
            LocalDate dataReuniao = null;
            int mesesAdicionados = 0;
            do {
                dataReuniao = anoMesReuniao
                        //.atDay(anoMesReuniao.getMonth().minLength())
                        .atEndOfMonth() // Não existe um atBeginOfMonth() ?
                        .plusMonths(mesesAdicionados++)
                        .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

                System.out.println(formatador.format(dataReuniao));
            } while (!dataReuniao.getMonth().equals(Month.DECEMBER));
        }
    }

    private Year solicitarAnoReuniao(Scanner scanner) {
        Objects.requireNonNull(scanner);
        while (true) {
            try {
                System.out.print("Ano: ");
                return Year.parse(scanner.nextLine());
            } catch (RuntimeException e) {
                System.out.println("Ano inválido. Tente novamente.");
            }
        }
    }

    private Month solicitarMesInicial(Scanner scanner) {
        Objects.requireNonNull(scanner);
        while (true) {
            try {
                System.out.print("Mês inicial: ");
                return Month.of(Integer.parseInt(scanner.nextLine()));
            } catch (RuntimeException e) {
                System.out.println("Mês inválido. Tente novamente.");
            }
        }
    }

    private DateTimeFormatter obterFormatadorComLocalePtBR(String pattern) {
        return DateTimeFormatter
                .ofPattern(pattern)
                .withLocale(new Locale("pt", "BR"));
    }
}