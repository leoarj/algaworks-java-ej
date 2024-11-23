package com.algaworks.comercial.repositorio.mysql;

import com.algaworks.comercial.repositorio.PersistenciaException;
import com.algaworks.comercial.repositorio.RepositoryFactory;
import com.algaworks.comercial.repositorio.VendaRepositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Adiciona o conceito de fábrica de objetos (Factory).
 * Implementando {@link AutoCloseable} para fechar recursos na destruição do objeto,
 * podendo utilizar uma instância dessa classe com try-with-resources.
 */
public class MySQLRepositoryFactory implements RepositoryFactory {

    private final Connection conexao;

    public MySQLRepositoryFactory(Properties properties) {
        try {
            this.conexao = DriverManager
                    .getConnection(
                            properties.getProperty("connection.url"),
                            properties.getProperty("connection.user"),
                            properties.getProperty("connection.password"));
        } catch (SQLException e) {
            throw new PersistenciaException("Erro estabelecendo conexão", e);
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
