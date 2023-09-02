package com.github.leoarj.algaworks.course.ej.datetime.api.iso8601;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/*
* Exemplo de uso da nova API de data/hora do Java.
*
* Introduzida no Java 8, segue o padrão 8601 e trabalha com a precisão de nanosegundos.
* Resolve diversos problemas da API legada e foi melhor desenhada com um estilo mais fluente.
*
* * Pacote: java.time
*
* * Algumas classes:
* - Instant = Representa um instante na linha do tempo, acompanha a ZoneID, respeitando o offset a partir do UTC
* (utilizada quando se precisa determinar o acontecimento exato de um evento e se necessita do deslocamento no tempo).
* - LocalDate = Representa uma data local sem o ZoneID. Utilizada para datas locais onde se sabe o contexto e
* não se necessita do offset.
* - LocalTime = Mesmo propósito que LocalDate, porém voltado a parte do horário.
* - LocalDateTime = Classe com composição de LocalDate+LocalTime.
* - DateTimeFormater = Representa um formatador, que respeita um padrão de formatação, para impressão de objetos temporais
* e interpretação de objetos temporais que estão em texto.
*
* As interfaces base da hierarquia de objetos temporais são:
* - TemporalAccessor = Objetos somente-leitura de tempo.
*   - Temporal = Herda TemporalAccessor. Objetos leitura-escrita de tempo.
*/

public class AgendamentoEvento {

    public static void main(String[] args) {
        AgendamentoEvento agendamentoEvento = new AgendamentoEvento();
        agendamentoEvento.agendar();
    }

    public void agendar() {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalDate data = solicitarData(scanner, "dd/MM/yyyy");
            LocalTime horario = solicitarHora(scanner, "HH:mm");

            LocalDateTime dataHora = LocalDateTime.of(data, horario);

            DateTimeFormatter formatadorImpressao = DateTimeFormatter
                    .ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT)
                    .withLocale(new Locale("pt", "BR"));

            System.out.printf("Evento agendado para %s", formatadorImpressao.format(dataHora));
        }
    }

    private LocalDate solicitarData(Scanner scanner, String pattern) {
        try {
            Objects.requireNonNull(scanner);

            System.out.print("Data do evento: ");
            String dataInput = scanner.nextLine();

            DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern(pattern);

            return LocalDate.parse(dataInput, formatadorData);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Tente novamente.");
            return solicitarData(scanner, pattern);
        }
    }

    private LocalTime solicitarHora(Scanner scanner, String pattern) {
        try {
            Objects.requireNonNull(scanner);

            System.out.print("Horário do evento: ");
            String horarioInput = scanner.nextLine();

            DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern(pattern);

            return LocalTime.parse(horarioInput, formatadorHora);
        } catch (DateTimeParseException e) {
            System.out.println("Horário inválido. Tente novamente.");
            return solicitarHora(scanner, pattern);
        }
    }
}