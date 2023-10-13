package com.github.leoarj.algaworks.course.ej.functional.streams.api;

import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.ServicoDeVenda;
import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.Venda;

import java.util.List;

public class Principal1 {

    public static void main(String[] args) {
        var servicoDeVenda = new ServicoDeVenda();
        List<Venda> vendas = servicoDeVenda.obterTodas();

//        for (Venda venda : vendas) {
//            if (venda.isFechada()) {
//                System.out.println(venda);
//            }
//        }

        /*
         * - Obtém um stream (fluxo) da fonte de dados (List de Venda);
         * - Operação intermediária filter aplica lógica de filtro no stream
         * a patir de uma method reference de Venda;
         * - Operação terminal forEach realiza o consumo de elementos do fluxo.
         */

        vendas.stream()
                .filter(Venda::isFechada)
                .forEach(System.out::println);
    }

}
