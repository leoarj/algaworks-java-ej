package com.algaworks.comercial.repositorio;

import com.algaworks.comercial.repositorio.memory.MemoryRepositoryFactory;
import com.algaworks.comercial.repositorio.mysql.MySQLRepositoryFactory;

/**
 * Exemplo do conceito de Abstract Factory.
 */
public interface RepositoryFactory extends AutoCloseable {

    /**
     * @param implementation Tipo da implementação da fábrica de repositórios (MYSQL, Em-Memória).<br/>
     * {@return Uma instância conforme implementação escolhida}
     */
    public static RepositoryFactory getInstance(RepositoryFactoryImplementation implementation) {
        return switch (implementation) {
            case MYSQL -> new MySQLRepositoryFactory();
            case IN_MEMORY -> new MemoryRepositoryFactory();
        };
    }

    VendaRepositorio createVendaRepositorio();

    @Override
    void close();
}
