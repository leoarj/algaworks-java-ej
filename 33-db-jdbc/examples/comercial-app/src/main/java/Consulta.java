import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class Consulta {

    /**
     * JDBC (Java Database Connectivity)
     * É a API do Java para acesso a banco de dados, abstraindo as diversas implementações de protocolo de cada DB.
     * É uma API de mais baixo nível.
     *
     * O JDBC já vem junto com o JDK.
     *
     * As implementações para a especificação JDBC são os chamados drivers JDBC.
     *
     * Neste projeto de exemplo foi utilizado o MySQL ConnectorJ
     * https://dev.mysql.com/downloads/connector/j/
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pesquisa por nome: ");
        String nomePesquisa = scanner.nextLine();

        /*
        * Statement = Interface que representa uma declaração/comando,
        * para execução de comandos SQL, retornando um ResultSet.
        *
        * ResultSet = Interface que representa um conjunto de resultados,
        * podendo por meio dele percorrer as linhas retornadas e obter os valores das colunas.
        *
        * Tanto Connection, quanto Statemet e ResultSet são AutoCloseable,
        * o que se faz necessário o uso de try-with-resources, porém ResultSet já é fechado
        * quando o Statement correspondente é fechado.
        */

        /*
        * PreparedStatement = Interface que representa uma instrução SQL pré-compilada.
        * Tem benefícios como:
        * - Não ter que concatenar valores dentro da instrução,
        * pois permite a definição de parâmetros na instrução e expansão dos mesmos antes de executar.
        * - É mais performática, porque mantém a query pronta para execução por múltiplas vezes.
        * - Trata o problema de SQL Injection.
        */
        try (Connection conexao = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/comercial", "root", "root123456");
             PreparedStatement comando = conexao.prepareStatement(
                     "select * from venda where nome_cliente like ?")) {
            comando.setString(1, "%" + nomePesquisa + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Long id = resultado.getLong("id");
                String nomeCliente = resultado.getString("nome_cliente");
                BigDecimal valorTotal = resultado.getBigDecimal("valor_total");
                Date dataPagamento = resultado.getDate("data_pagamento");

                System.out.printf("%d - %s - R$%.2f - %s%n",
                        id, nomeCliente, valorTotal, dataPagamento);
            }

//            System.out.println(conexao.getClass());
//            System.out.println(comando.getClass());
//            System.out.println(resultado.getClass());
        } catch (SQLException e) {
            System.out.println("Erro de banco de dados");
            e.printStackTrace();
        }
    }
}
