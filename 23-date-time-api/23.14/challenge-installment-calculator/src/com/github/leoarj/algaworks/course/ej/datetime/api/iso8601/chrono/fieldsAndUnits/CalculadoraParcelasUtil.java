package com.github.leoarj.algaworks.course.ej.datetime.api.iso8601.chrono.fieldsAndUnits;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class CalculadoraParcelasUtil {

    public void calcular() {
        try (Scanner scanner = new Scanner(System.in)) {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate dataPrimeiraParcela = solicitarPrimeiraParcela(scanner, formatador);
            int quantidadeParcelas = solicitarQuantidadeParcelas(scanner);

            CalculadoraParcelas calculadoraParcelas = new CalculadoraParcelas(dataPrimeiraParcela, quantidadeParcelas);

            for (ParcelaCalculada parcela : calculadoraParcelas) {
                System.out.printf("Parcela #%d - %s%n", parcela.numeroParcela(), formatador.format(parcela.dataParcela()));
            }

        } catch (DateTimeException e) {
            System.err.printf("Erro ao calcular parcelas! Verifique os dados informados.%nCausa: %s", e.getMessage());
        }
    }

    private LocalDate solicitarPrimeiraParcela(Scanner scanner, DateTimeFormatter formatador) {
        Objects.requireNonNull(scanner);
        Objects.requireNonNull(formatador);

        while (true) {
            try {
                System.out.print("Data da primeira parcela: ");
                String dataPrimeiraParcelaInput = scanner.nextLine();

                return LocalDate.parse(dataPrimeiraParcelaInput, formatador);
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