package com.github.leoarj.algaworks.course.ej.datetime.api.iso8601.chrono.fieldsAndUnits;

import java.time.LocalDate;
import java.util.Iterator;

public class CalculadoraParcelas implements Iterable<ParcelaCalculada> {

    private final LocalDate dataPrimeiraParcela;
    private final int quantidadeParcelas;

    public CalculadoraParcelas(final LocalDate dataPrimeiraParcela, final int quantidadeParcelas) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
        this.quantidadeParcelas = quantidadeParcelas;
    }

    @Override
    public Iterator<ParcelaCalculada> iterator() {
        return new CalculadoraParcelasIterator(dataPrimeiraParcela, quantidadeParcelas);
    }
}