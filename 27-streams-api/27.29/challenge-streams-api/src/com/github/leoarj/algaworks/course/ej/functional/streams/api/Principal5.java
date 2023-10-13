package com.github.leoarj.algaworks.course.ej.functional.streams.api;

import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.ServicoDeVenda;
import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.Venda;

import java.util.List;

public class Principal5 {

    public static void main(String[] args) {
        var servicoDeVenda = new ServicoDeVenda();
        List<Venda> vendas = servicoDeVenda.obterTodas();

        int quantidadeItensVendidos = 0;
//        for (Venda venda : vendas) {
//            if (venda.isFechada()) {
//                for (Venda.Item item : venda.getItens()) {
//                    quantidadeItensVendidos += item.quantidade();
//                }
//            }
//        }

        /*
         * - Obtém um stream (fluxo) da fonte de dados (List de Venda);
         * - Operação intermediária filter aplica lógica de filtro no stream
         * a patir de uma method reference de Venda;
         * - Operação intermediária flatMap retorna um valor "achatado"
         * (valor direto) para se obter o stream de outra coleção;
         * - Operação intermediária mapToInt realiza a transformação
         * para um stream de inteiros (primitivo) para se evitar boxing/unboxig
         * e ter acesso a alguns métodos de conveniência.
         * Operação terminal sum acumula (somando) em um resultado único.
         */

        quantidadeItensVendidos = vendas.stream()
                        .filter(Venda::isFechada)
                        .flatMap(venda -> venda.getItens().stream())
                        .mapToInt(Venda.Item::quantidade)
                        .sum();

        System.out.println(quantidadeItensVendidos);
    }

}
