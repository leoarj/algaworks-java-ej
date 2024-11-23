package com.algaworks.comercial.repositorio.memory;

import com.algaworks.comercial.entidade.Venda;
import com.algaworks.comercial.repositorio.VendaRepositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simples exemplo para implementação em memória,
 * para demonstrar diferentes implementações a partir da factory.
 */
public class MemoryVendaRepositorio implements VendaRepositorio {

    private static long proximoId = 1L;
    private static final List<Venda> vendas = new ArrayList<>();

    @Override
    public Venda adicionar(Venda venda) {
        var novaVenda = new Venda(proximoId++, venda.getNomeCliente(),
                venda.getValorTotal(), venda.getDataPagamento());

        vendas.add(novaVenda);

        return novaVenda;
    }

    @Override
    public List<Venda> consultar() {
        return Collections.unmodifiableList(vendas);
    }
}
