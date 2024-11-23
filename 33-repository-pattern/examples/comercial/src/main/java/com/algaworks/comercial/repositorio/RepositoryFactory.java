package com.algaworks.comercial.repositorio;

import com.algaworks.comercial.repositorio.mysql.MySQLVendaRepositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Adiciona o conceito de fábrica de objetos (Factory).
 * Implementando {@link AutoCloseable} para fechar recursos na destruição do objeto,
 * podendo utilizar uma instância dessa classe com try-with-resources.
 */
public class RepositoryFactory implements AutoCloseable {

    private final Connection conexao;

    public RepositoryFactory() {
        try {
            this.conexao = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/comercial", "root", "root123456");
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }

    public VendaRepositorio createVendaRepositorio() {
        return new MySQLVendaRepositorio(conexao);
    }

    @Override
    public void close() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }
}
