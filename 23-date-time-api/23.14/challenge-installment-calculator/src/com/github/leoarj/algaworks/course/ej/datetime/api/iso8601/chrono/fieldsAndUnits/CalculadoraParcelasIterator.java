package com.github.leoarj.algaworks.course.ej.datetime.api.iso8601.chrono.fieldsAndUnits;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CalculadoraParcelasIterator implements Iterator<ParcelaCalculada> {

    private final LocalDate dataPrimeiraParcela;
    private final int quantidadeParcelas;
    private int quantidadeParcelasCalculadas;

    public CalculadoraParcelasIterator(final LocalDate dataPrimeiraParcela, final int quantidadeParcelas) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
        this.quantidadeParcelas = quantidadeParcelas;
        this.quantidadeParcelasCalculadas = 0;
    }

    @Override
    public boolean hasNext() {
        return quantidadeParcelasCalculadas < quantidadeParcelas;
    }

    @Override
    public ParcelaCalculada next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Todas as parcelas já foram calculadas");
        }

        return new ParcelaCalculada(
                dataPrimeiraParcela.plusMonths(quantidadeParcelasCalculadas++), quantidadeParcelasCalculadas);
    }
}