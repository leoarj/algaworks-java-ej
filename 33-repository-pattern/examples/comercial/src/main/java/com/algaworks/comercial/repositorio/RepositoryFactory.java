package com.algaworks.comercial.repositorio;

import com.algaworks.comercial.repositorio.memory.MemoryRepositoryFactory;
import com.algaworks.comercial.repositorio.mysql.MySQLRepositoryFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Exemplo do conceito de Abstract Factory.
 */
public interface RepositoryFactory extends AutoCloseable {

    /**
     * Tipo da implementação da fábrica de repositórios (MYSQL, Em-Memória).<br/>
     * {@return Uma instância conforme implementação escolhida}
     */
    public static RepositoryFactory getInstance() {
        Properties properties = new Properties();
        try (var inputStream = RepositoryFactory.class.getResourceAsStream("/persistence.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new PersistenciaException("Erro carregando configurações", e);
        }

        try {
            var implementation = RepositoryFactoryImplementation.valueOf(
                    properties.getProperty("repository-implementation").toUpperCase());

            return switch (implementation) {
                case MYSQL -> new MySQLRepositoryFactory(properties);
                case MEMORY -> new MemoryRepositoryFactory();
            };
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Implementação de repositório não existe", e);
        }
    }

    VendaRepositorio createVendaRepositorio();

    @Override
    void close();
}
