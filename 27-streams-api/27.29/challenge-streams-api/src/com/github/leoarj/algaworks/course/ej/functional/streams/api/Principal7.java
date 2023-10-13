package com.github.leoarj.algaworks.course.ej.functional.streams.api;

import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.Cliente;
import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.ServicoDeVenda;
import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.Venda;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal7 {

    public static void main(String[] args) {
        var servicoDeVenda = new ServicoDeVenda();
        List<Venda> vendas = servicoDeVenda.obterTodas();

//        Map<String, Long> vendasRealizadasPorCliente = new HashMap<>();
//
//        for (Venda venda : vendas) {
//            if (venda.isFechada()) {
//                String nomeCliente = venda.getCliente().nome();
//                Long quantidadeVendas = vendasRealizadasPorCliente.get(nomeCliente);
//                if (quantidadeVendas == null) {
//                    quantidadeVendas = 0L;
//                }
//
//                vendasRealizadasPorCliente.put(nomeCliente, quantidadeVendas + 1);
//            }
//        }
//
//        System.out.println(vendasRealizadasPorCliente);

        /*
         * - Obtém um stream (fluxo) da fonte de dados (List de Venda);
         * - Operação intermediária filter aplica lógica de filtro no stream
         * a patir de uma method reference de Venda;
         * - Operação intermediária map realiza o mapeamento/transformação
         * do stream da coleção em outro stream;
         * Função terminal coletora coleta os resultados em uma collection (Map)
         * agrupando os elementos por um classificador (Cliente::nome) que será a chave do Map
         * e reduzindo/acumulando os resultados conforme um coletor (downstream) no que será
         * o valor agrupado para cada chave.
         * Obs.: No caso, está acumulando conforme a contagem de vendas onde ocorre o mesmo nome do cliente
         * (a chave em vez de String também poderia ser da classe Cliente).
         */

        /*
         * Utilizando coletores agregados padrão:
         */
        Map<String, Long> vendasRealizadasPorCliente = vendas.stream()
                .filter(Venda::isFechada)
                .map(Venda::getCliente)
                .collect(Collectors.groupingBy(Cliente::nome, Collectors.counting()));

        /*
         * Utilizando coletores customizados por lambdas functions:
         */
//        Map<String, Long> vendasRealizadasPorCliente = vendas.stream()
//                .filter(Venda::isFechada)
//                .map(Venda::getCliente)
//                .collect(HashMap::new,
//                        (mapa, cliente) ->
//                            mapa.put(cliente.nome(),
//                                    (mapa.containsKey(cliente.nome())) ? mapa.get(cliente.nome())+1L : 1L),
//                        (mapa1, mapa2) -> mapa1.putAll(mapa2));

        /*
         * Utilizando coletores customizados por method reference:
         */
//        Map<String, Long> vendasRealizadasPorCliente = vendas.stream()
//                .filter(Venda::isFechada)
//                .map(Venda::getCliente)
//                .collect(HashMap::new,
//                        Principal7::incrementarVendasRealizadasPorCliente,
//                        HashMap::putAll);

        System.out.println(vendasRealizadasPorCliente);
    }

    private static void incrementarVendasRealizadasPorCliente(Map<String, Long> mapa, Cliente cliente) {
        Long quantidadeVendas = mapa.get(cliente.nome());

        mapa.put(cliente.nome(),
                (mapa.containsKey(cliente.nome()) ? quantidadeVendas + 1L : 1L));
    }
}

