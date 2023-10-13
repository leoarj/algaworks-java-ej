package com.github.leoarj.algaworks.course.ej.functional.streams.api;

import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.ServicoDeVenda;
import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.Venda;

import java.util.List;

public class Principal3 {

    public static void main(String[] args) {
        var servicoDeVenda = new ServicoDeVenda();
        List<Venda> vendas = servicoDeVenda.obterTodas();

//        List<String> descricoes = new ArrayList<>();
//        for (Venda venda : vendas) {
//            if (venda.isFechada()) {
//                for (Venda.Item item : venda.getItens()) {
//                    if (!descricoes.contains(item.descricao())) {
//                        descricoes.add(item.descricao());
//                    }
//                }
//            }
//        }
//
//        Collections.sort(descricoes);
//
//        for (String descricao : descricoes) {
//            System.out.println(descricao);
//        }

        /*
         * - Obtém um stream (fluxo) da fonte de dados (List de Venda);
         * - Operação intermediária filter aplica lógica de filtro no stream
         * a patir de uma method reference de Venda;
         * - Operação intermediária flatMap retorna um valor "achatado"
         * (valor direto) para se obter o stream de outra coleção;
         * - Operação intermediária map realiza o mapeamento/transformação
         * do stream da coleção em outro stream a partir de uma method reference
         * do tipo de classe do stream anterior;
         * - Operação intermediária distinct aplica a remoção de elementos duplicados.
         * - Operação intermediária sorted realiza a comparação a partir da implementação de Comparable
         * para ordenar pela ordem natural definida;
         * - Operação terminal forEach realiza o consumo de elementos do fluxo.
         */

        vendas.stream()
                .filter(Venda::isFechada)
                .flatMap(venda -> venda.getItens().stream())
                .map(Venda.Item::descricao)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

}
