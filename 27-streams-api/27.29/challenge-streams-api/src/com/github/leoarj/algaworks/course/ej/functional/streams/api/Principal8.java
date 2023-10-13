package com.github.leoarj.algaworks.course.ej.functional.streams.api;

import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.Cliente;
import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.ServicoDeVenda;
import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.Venda;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal8 {

    public static void main(String[] args) {
        var servicoDeVenda = new ServicoDeVenda();
        List<Venda> vendas = servicoDeVenda.obterTodas();

        /*
         * Utilizando coletores customizados por lambdas expressions,
         * caso não tivessemos o Valor Total por meio da venda:
         *
         * Não é uma boa prática pois a expressão ficou longa (acima de 3 linhas),
         * sendo melhor refatorar para uma função separada.
         */
//        Map<String, BigDecimal> valorPorCliente = vendas.stream()
//                .filter(Venda::isFechada)
//                .collect(HashMap::new,
//                        (mapa, venda) -> {
//                            BigDecimal valorVenda = venda.getItens().stream()
//                                    .map(Venda.Item::calcularValorTotal)
//                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//                            String nomeCliente = venda.getCliente().nome();
//
//                            mapa.put(nomeCliente,
//                                    (mapa.containsKey(nomeCliente) ?
//                                            mapa.get(nomeCliente).add(valorVenda) : valorVenda));
//                        },
//                        HashMap::putAll);

        /*
         * Utilizando coletores customizados por lambdas expressions,
         * acessando o Valor Total por meio da venda:
         *
         * Não é uma boa prática pois a expressão ficou longa (acima de 3 linhas),
         * sendo melhor refatorar para uma função separada.
         */
//        Map<String, BigDecimal> valorPorCliente = vendas.stream()
//                .filter(Venda::isFechada)
//                .collect(HashMap::new,
//                        (mapa, venda) -> {
//                            BigDecimal valorVenda = venda.getValorTotal();
//                            String nomeCliente = venda.getCliente().nome();
//
//                            mapa.put(nomeCliente,
//                                    (mapa.containsKey(nomeCliente) ?
//                                            mapa.get(nomeCliente).add(valorVenda) : valorVenda));
//                        },
//                        HashMap::putAll);

        /*
         * Utilizando coletores customizados por method reference:
         */
//        Map<String, BigDecimal> valorPorCliente = vendas.stream()
//                .filter(Venda::isFechada)
//                .collect(HashMap::new,
//                        Principal8::incrementarValorVendasRealizadasPorCliente,
//                        HashMap::putAll);

        /*
        * Utilizando coletor padrão reducing():
        */
        Map<String, BigDecimal> valorPorCliente = vendas.stream()
                .filter(Venda::isFechada)
                .collect(Collectors.groupingBy(venda -> venda.getCliente().nome(),
                        Collectors.reducing(BigDecimal.ZERO, Venda::getValorTotal, BigDecimal::add)));

        System.out.println(valorPorCliente);
    }

//    private static void incrementarValorVendasRealizadasPorCliente(Map<String, BigDecimal> mapa, Venda venda) {
//        BigDecimal valorVenda = venda.getValorTotal();
//        String nomeCliente = venda.getCliente().nome();
//
//        mapa.put(nomeCliente,
//                (mapa.containsKey(nomeCliente) ?
//                        mapa.get(nomeCliente).add(valorVenda) : valorVenda));
//    }
}
