package com.algaworks.comercial;

import com.algaworks.comercial.entidade.Venda;
import com.algaworks.comercial.repositorio.RepositoryFactory;
import com.algaworks.comercial.repositorio.RepositoryFactoryImplementation;
import com.algaworks.comercial.servico.CadastroVendaServico;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) throws SQLException {
        /*
        * Centraliza controle da conexão.
        */
        try (var repositoryFactory = RepositoryFactory.getInstance()) {
            var vendaRepositorio = repositoryFactory.createVendaRepositorio();
            var cadastroVendaServico = new CadastroVendaServico(vendaRepositorio);
            Venda vendaCadastrada = cadastroVendaServico.cadastrar("José da Silva",
                    new BigDecimal("12300.87"), LocalDate.parse("2023-04-19"));

            System.out.println("Venda cadastrada: " + vendaCadastrada);

            System.out.println("Listando todas as vendas");
            var todasVendas = vendaRepositorio.consultar();
            todasVendas.forEach(System.out::println);
        }
    }

}