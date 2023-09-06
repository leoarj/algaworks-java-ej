package com.github.leoarj.algaworks.course.ej.datetime.api.iso8601.chrono.fieldsAndUnits.old;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class CalculadoraParcelas2 {

    public void calcular() {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalDate dataPrimeiraParcela = solicitarPrimeiraParcela(scanner, "dd/MM/yyyy");
            int quantidadeParcelas = solicitarQuantidadeParcelas(scanner);

            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            int diaPrimeiraParcela = dataPrimeiraParcela.getDayOfMonth();
            LocalDate dataProximaParcela = dataPrimeiraParcela;
            int quantidadeParcelasProcessadas = 0;

            do {
                System.out.printf("Parcela #%d - %s%n", ++quantidadeParcelasProcessadas, formatador.format(dataProximaParcela));

                if (quantidadeParcelasProcessadas < quantidadeParcelas) {

                    dataProximaParcela = dataProximaParcela.plusMonths(1);
                    //int diaMaximoProximaParcela = dataProximaParcela.lengthOfMonth();
                    int diaMaximoProximaParcela = Month.from(dataProximaParcela).maxLength();

                    dataProximaParcela = (diaPrimeiraParcela > diaMaximoProximaParcela ?
                            dataProximaParcela.withDayOfMonth(diaMaximoProximaParcela) :
                            dataProximaParcela.withDayOfMonth(diaPrimeiraParcela));
                }
            } while (quantidadeParcelasProcessadas < quantidadeParcelas);

        } catch (DateTimeException e) {
            System.err.printf("Erro ao calcular parcelas! Verifique os dados informados.%nCausa: %s", e.getMessage());
        }
    }

    private LocalDate solicitarPrimeiraParcela(Scanner scanner, String pattern) {
        Objects.requireNonNull(scanner);

        while (true) {
            try {
                System.out.print("Data da primeira parcela: ");
                String dataPrimeiraParcelaInput = scanner.nextLine();

                return LocalDate.parse(dataPrimeiraParcelaInput, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Tente novamente.");
            }
        }
    }

    private int solicitarQuantidadeParcelas(Scanner scanner) {
        Objects.requireNonNull(scanner);

        while (true) {
            try {
                System.out.print("Quantidade de parcelas: ");
                return scanner.nextInt();
            } catch (RuntimeException e) {
                System.out.println("Houve um erro. Tente novamente.");
            }
        }
    }
}