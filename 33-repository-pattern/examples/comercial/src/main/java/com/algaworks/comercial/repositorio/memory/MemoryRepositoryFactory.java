package com.algaworks.comercial.repositorio.memory;

import com.algaworks.comercial.repositorio.RepositoryFactory;
import com.algaworks.comercial.repositorio.VendaRepositorio;

public class MemoryRepositoryFactory implements RepositoryFactory {
    @Override
    public VendaRepositorio createVendaRepositorio() {
        return new MemoryVendaRepositorio();
    }

    @Override
    public void close() {
    }
}
