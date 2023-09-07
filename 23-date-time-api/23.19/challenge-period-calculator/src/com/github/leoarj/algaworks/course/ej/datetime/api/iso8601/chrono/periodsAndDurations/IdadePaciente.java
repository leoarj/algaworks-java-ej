package com.github.leoarj.algaworks.course.ej.datetime.api.iso8601.chrono.periodsAndDurations;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

/*
* O pacote java.time provê algumas classes para se trabalhar com períodos entre datas/instantes no tempo.
*
* Essas classes são úteis para cálculos entre datas e manipulação das mesmas, resolvendo diversas
* possibilidades que ocorrem no mundo real.
*
* - Period = (date-based) Período em unidade em termos de anos, meses e dias.
* Pode ser utilizada com LocalDate por exemplo.
*
* Exemplo de representação no padrão ISO8061:
* - P45D (Onde P = Period, 45 = Quantidade da unidade, D = Tipo da unidade (Days))
* - P1M (Onde P = Period, 1 = Quantidade da unidade, M = Tipo da unidade (Month))
* - P1Y5M10D (Onde P = Period, 1,5,10 = Quantidade da unidade (Year, Month, Day respectivamente),
* Y = Tipo da unidade (Year), M = Month, D = Day)
*
* - Duration = (time-based) Duração baseada em termos de segundos e nanosegundos.
* Pode ser utilizada com LocalDateTime e LocalTime por exemplo.
*
* Exemplo de representação no padrão ISO8061:
* - PT5H (Onde P = Period, T = Time, 5 = Quantidade da unidade, H = Tipo da unidade (Hour))
*
* Obs.: No ISO 8601 não há essa separação, porém o Java realiza essa diferenciação.
*/

public class IdadePaciente {

    public static void main(String[] args) {
        IdadePaciente idadePaciente = new IdadePaciente();
        idadePaciente.calcular("dd/MM/yyyy");
    }

    public void calcular(String formatoData) {
        try (Scanner scanner = new Scanner(System.in)) {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern(formatoData);
            LocalDate dataNascimentoPaciente = solicitarDataNascimentoPaciente(scanner, formatador);
            LocalDate dataAtual = LocalDate.now();

            if (dataNascimentoPaciente.isAfter(dataAtual)) {
                throw new RuntimeException("Data de nascimento não pode ser maior que a data atual");
            }

            //Period idadePaciente = Period.between(dataNascimentoPaciente, dataAtual);
            Period idadePaciente = dataNascimentoPaciente.until(dataAtual);

            System.out.printf("Paciente tem %d %s, %d %s e %s %s de vida",
                    idadePaciente.getYears(), (idadePaciente.getYears() > 1 ? "anos" : "ano"),
                    idadePaciente.getMonths(), (idadePaciente.getMonths() > 1 ? "meses" : "mês"),
                    idadePaciente.getDays(), (idadePaciente.getDays() > 1 ? "dias" : "dia"));
        }  catch (RuntimeException e) {
            System.err.printf("Erro ao calcular idade! Verifique os dados informados.%nCausa: %s", e.getMessage());
        }
    }

    private LocalDate solicitarDataNascimentoPaciente(Scanner scanner, DateTimeFormatter formatador) {
        Objects.requireNonNull(scanner);
        Objects.requireNonNull(formatador);
        while (true) {
            try {
                System.out.print("Data de nascimento: ");
                return LocalDate.parse(scanner.nextLine(), formatador);
            } catch (RuntimeException e) {
                System.out.println("Data inválida. Tente novamente");
            }
        }
    }
}