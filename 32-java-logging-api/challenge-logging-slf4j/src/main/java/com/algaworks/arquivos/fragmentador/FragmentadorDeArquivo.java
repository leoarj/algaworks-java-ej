package com.algaworks.arquivos.fragmentador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.*;

public class FragmentadorDeArquivo {

    private static final Logger logger = LoggerFactory.getLogger(FragmentadorDeArquivo.class);

    private final Path arquivoOrigem;
    private final int tamanhoFragmento;

    public FragmentadorDeArquivo(Path arquivoOrigem, int tamanhoFragmento) {
        Objects.requireNonNull(arquivoOrigem);
        validarTamanhoFragmento(tamanhoFragmento);

        this.arquivoOrigem = arquivoOrigem;
        this.tamanhoFragmento = tamanhoFragmento;

        if (this.tamanhoFragmento < (1024 * 100)) {
            logger.warn("Tamanho dos fragmentos muito pequeno. Considere aumentar para no mínimo {}kb", 100);
        }
    }

    public void fragmentar() throws IOException {
        logger.debug("Fragmentação invocada para arquivo original '{}' com fragmentos de até {} bytes",
                arquivoOrigem, tamanhoFragmento);
        try (ByteChannel canalLeituraArquivoOrigem = Files.newByteChannel(arquivoOrigem, READ)) {
            final ByteBuffer buffer = ByteBuffer.allocate(tamanhoFragmento);

            int quantidadeFragmentosLidos = 1;

            while (canalLeituraArquivoOrigem.read(buffer) > 0) {
                logger.trace("Lendo buffer de fragmento {}", quantidadeFragmentosLidos);
                buffer.flip();
                Path arquivoDestino = obterFragmentoArquivo(arquivoOrigem, quantidadeFragmentosLidos++);
                escreverFragmentoArquivo(arquivoDestino, buffer);
                buffer.clear();
            }
        }
    }

    private static void validarTamanhoFragmento(int tamanhoFragmento) {
        if (tamanhoFragmento < 1) {
            throw new IllegalArgumentException("Tamanho do fragmento inválido");
        }
    }

    private Path obterFragmentoArquivo(Path arquivoOrigem, int sequencia) {
        return Path.of(String.format("%s.%d", arquivoOrigem.toString(), sequencia));
    }

    private void escreverFragmentoArquivo(Path arquivoDestino, final ByteBuffer buffer) throws IOException {
        logger.trace("Escrevendo fragmento em {}", arquivoDestino.getFileName());
        try (ByteChannel canalEscritaArquivoDestino = Files.newByteChannel(arquivoDestino, CREATE_NEW, WRITE)) {
            canalEscritaArquivoDestino.write(buffer);
        }
    }
}
