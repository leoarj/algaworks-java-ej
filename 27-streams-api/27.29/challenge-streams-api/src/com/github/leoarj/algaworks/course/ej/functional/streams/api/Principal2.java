package com.github.leoarj.algaworks.course.ej.functional.streams.api;

import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.Cliente;
import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.ServicoDeVenda;
import com.github.leoarj.algaworks.course.ej.functional.streams.api.comercial.Venda;

import java.util.*;

public class Principal2 {

    public static void main(String[] args) {
        var servicoDeVenda = new ServicoDeVenda();
        List<Venda> vendas = servicoDeVenda.obterTodas();

//        List<Cliente> clientes = new ArrayList<>();
//        for (Venda venda : vendas) {
//            if (venda.isFechada() && !clientes.contains(venda.getCliente())) {
//                clientes.add(venda.getCliente());
//            }
//        }
//        clientes.sort(Comparator.comparing(Cliente::nome));
//
//        for (Cliente cliente : clientes) {
//            System.out.println(cliente);
//        }

        /*
         * - Obtém um stream (fluxo) da fonte de dados (List de Venda);
         * - Operação intermediária filter aplica lógica de filtro no stream
         * a patir de uma method reference de Venda;
         * - Operação intermediária distinct aplica a remoção de elementos duplicados
         * Obs.: Para isso, o método equals de Object deve estar implementado
         * (records implementam por padrão);
         * - Operação intermediária sorted realiza a comparação a partir de um critério definido;
         * - Operação terminal forEach realiza o consumo de elementos do fluxo.
         */

        vendas.stream()
                .filter(Venda::isFechada)
                .map(Venda::getCliente)
                .distinct()
                .sorted(Comparator.comparing(Cliente::nome))
                .forEach(System.out::println);
    }

}
