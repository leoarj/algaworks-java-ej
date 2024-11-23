package com.algaworks.comercial.repositorio.mysql;

import com.algaworks.comercial.repositorio.PersistenciaException;
import com.algaworks.comercial.repositorio.RepositoryFactory;
import com.algaworks.comercial.repositorio.VendaRepositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Adiciona o conceito de fábrica de objetos (Factory).
 * Implementando {@link AutoCloseable} para fechar recursos na destruição do objeto,
 * podendo utilizar uma instância dessa classe com try-with-resources.
 */
public class MySQLRepositoryFactory implements RepositoryFactory {

    private final Connection conexao;

    public MySQLRepositoryFactory() {
        try {
            this.conexao = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/comercial", "root", "root123456");
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }

    @Override
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
