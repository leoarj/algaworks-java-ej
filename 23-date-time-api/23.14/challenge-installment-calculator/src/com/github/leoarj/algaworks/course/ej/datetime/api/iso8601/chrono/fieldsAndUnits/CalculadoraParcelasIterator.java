package com.github.leoarj.algaworks.course.ej.datetime.api.iso8601.chrono.fieldsAndUnits;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CalculadoraParcelasIterator implements Iterator<ParcelaCalculada> {

    private final int quantidadeParcelas;
    private final int diaPrimeiraParcela;
    private LocalDate dataParcelaAtual;
    private int quantidadeParcelasCalculadas;

    public CalculadoraParcelasIterator(final LocalDate dataPrimeiraParcela, final int quantidadeParcelas) {
        this.dataParcelaAtual = dataPrimeiraParcela;
        this.quantidadeParcelas = quantidadeParcelas;
        this.diaPrimeiraParcela = dataPrimeiraParcela.getDayOfMonth();
        this.quantidadeParcelasCalculadas = 0;
    }

    @Override
    public boolean hasNext() {
        return quantidadeParcelasCalculadas < quantidadeParcelas;
    }

    @Override
    public ParcelaCalculada next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Todas parcelas já foram calculadas");
        }

        if (quantidadeParcelasCalculadas == 0) {
            return new ParcelaCalculada(dataParcelaAtual, ++quantidadeParcelasCalculadas);
        }

        LocalDate dataProximaParcela = dataParcelaAtual.plusMonths(1);
        int diaMaximoProximaParcela = dataProximaParcela.lengthOfMonth();

        dataProximaParcela = (diaPrimeiraParcela > diaMaximoProximaParcela ?
                dataProximaParcela.withDayOfMonth(diaMaximoProximaParcela) :
                dataProximaParcela.withDayOfMonth(diaPrimeiraParcela));

        dataParcelaAtual = dataProximaParcela;

        return new ParcelaCalculada(dataProximaParcela, ++quantidadeParcelasCalculadas);
    }
}