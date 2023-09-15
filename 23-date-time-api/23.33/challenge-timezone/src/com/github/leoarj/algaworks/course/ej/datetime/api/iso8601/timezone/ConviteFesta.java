package com.github.leoarj.algaworks.course.ej.datetime.api.iso8601.timezone;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
 * A API de date-time do Java permite a identificação de fusos horários com ZoneId e ZoneOffset.
 *
 * - ZoneId = Abstração de uma identificação de zona de fuso horário.
 *
 * - ZoneRegion = Representa uma região de fuso horário,
 * por exemplo [America/Sao_Paulo].
 * Obs.: Possui visibilidade default package e instâncias são obtidas/resolvidas automaticamente
 * pelo método ZoneId.of().
 *
 * - ZoneOffset = Representa um deslocamento de tempo de uma região.
 * Por exemplo, pode variar conforme regras locais, como horário de verão.
 *
 * - ZonedDateTime = Objeto temporal de data/hora com informação do fuso horário.
 *
 * - OffsetDateTime/OffsetTime = Representam data e hora ou somente hora com deslocamento do UTC.
 * Obs.: Muito visto na integração de webservices, porque os serviços precisam saber o deslocamento
 * de tempo para processar corretamente as informações.
 *
 * Com a API é possível realizar:
 *
 * - Identificação de fusos.
 *
 * - Instanciação de objetos temporais a partir de fusos.
 *
 * - Cálculos/conversões de/para de objetos temporais com ZonedDateTime entre outras classes.
 *
 * - Outras diversas operações com objetos temporais, resolvendo situações complexas,
 * a fim de evitarmos reinventar a roda ou gerar bugs tentando implementar algoritmos complexos
 * que já existem e que foram testados.
 *
 * - É uma API bem extensa, mas consideravelmente melhor desenhada e segura que a API legada.
 */

public class ConviteFesta {

    private static final String FORMATO_ENTRADA_DATA_LOCAL = "dd/MM/yyyy";
    private static final String FORMATO_ENTRADA_HORARIO_LOCAL = "HH:mm";
    private static final String TIME_ZONE_AMERICA_SAO_PAULO = "America/Sao_Paulo";
    private static final String TIME_ZONE_AMERICA_LOS_ANGELES = "America/Los_Angeles";
    private static final String TIME_ZONE_JAPAN = "Japan";
    private static final String FORMATO_SAIDA_DATA_HORARIO_CONVITE = "dd 'de' MMMM 'de' yyyy 'às' HH:mm (zzzz)";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalDate dataLocal = solicitarDataLocal(scanner, DateTimeFormatter.ofPattern(FORMATO_ENTRADA_DATA_LOCAL));
            LocalTime horarioLocal = solicitarHorarioLocal(scanner, DateTimeFormatter.ofPattern(FORMATO_ENTRADA_HORARIO_LOCAL));

            Collection<ZonedDateTime> datasConvites = new ConviteFesta()
                    .gerarConvites(
                            dataLocal.atTime(horarioLocal),
                            TIME_ZONE_AMERICA_SAO_PAULO,
                            TIME_ZONE_AMERICA_LOS_ANGELES,
                            TIME_ZONE_JAPAN);

            DateTimeFormatter formatadorDataConvite = DateTimeFormatter
                    .ofPattern(FORMATO_SAIDA_DATA_HORARIO_CONVITE)
                    .withLocale(new Locale("pt", "BR"));

            for (ZonedDateTime dataConvite : datasConvites) {
                System.out.println(formatadorDataConvite.format(dataConvite));
            }
        } catch (RuntimeException e) {
            System.err.printf(e.getMessage());
        }
    }

    public Collection<ZonedDateTime> gerarConvites(LocalDateTime dataHorarioLocal, String... timeZones) {
        Objects.requireNonNull(dataHorarioLocal);
        Objects.requireNonNull(timeZones);
        try {
            ZonedDateTime dataHorarioComTimeZoneLocal = dataHorarioLocal.atZone(ZoneId.systemDefault());
            Collection<ZonedDateTime> datasConvites = new ArrayList<>(timeZones.length);

            for (String timeZone : timeZones) {
                datasConvites.add(dataHorarioComTimeZoneLocal.withZoneSameInstant(ZoneId.of(timeZone)));
            }

            return Collections.unmodifiableCollection(datasConvites);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao gerar convites!", e);
        }
    }

    private static LocalDate solicitarDataLocal(Scanner scanner, DateTimeFormatter formatter) {
        Objects.requireNonNull(scanner);
        Objects.requireNonNull(formatter);
        while (true) {
            try {
                System.out.print("Data local da festa: ");
                return LocalDate.parse(scanner.nextLine(), formatter);
            } catch (RuntimeException e) {
                System.out.println("Data inválida. Tente novamente.");
            }
        }
    }

    private static LocalTime solicitarHorarioLocal(Scanner scanner, DateTimeFormatter formatter) {
        Objects.requireNonNull(scanner);
        Objects.requireNonNull(formatter);
        while (true) {
            try {
                System.out.print("Horário local da festa: ");
                return LocalTime.parse(scanner.nextLine(), formatter);
            } catch (RuntimeException e) {
                System.out.println("Horário inválido. Tente novamente.");
            }
        }
    }
}