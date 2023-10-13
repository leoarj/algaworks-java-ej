package com.github.leoarj.algaworks.course.ej.functional.streams.api;

import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.ServicoDeVenda;
import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.Venda;

import java.math.BigDecimal;
import java.util.List;

public class Principal4 {

    public static void main(String[] args) {
        var servicoDeVenda = new ServicoDeVenda();
        List<Venda> vendas = servicoDeVenda.obterTodas();

//        BigDecimal totalVendas = BigDecimal.ZERO;
//        for (Venda venda : vendas) {
//            if (venda.isFechada()) {
//                totalVendas = totalVendas.add(venda.getValorTotal());
//            }
//        }

        /*
         * - Obtém um stream (fluxo) da fonte de dados (List de Venda);
         * - Operação intermediária filter aplica lógica de filtro no stream
         * a patir de uma method reference de Venda;
         * - Operação intermediária map realiza o mapeamento/transformação
         * do stream da coleção em outro stream a partir de uma method reference
         * do tipo de classe do stream anterior;
         * - Operação terminal reduce retorna o resultado a partir de uma
         * lógica de acumulação (como os elementos vão ser acumulados entre si para fornecer um resultado único),
         * com base em um valor inicial.
         */

        BigDecimal totalVendas = vendas.stream()
                .filter(Venda::isFechada)
                .map(Venda::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(totalVendas);
    }

}
