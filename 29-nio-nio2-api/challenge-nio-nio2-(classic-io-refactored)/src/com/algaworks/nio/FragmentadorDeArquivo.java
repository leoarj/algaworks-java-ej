package com.algaworks.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.*;

/**
 * Objetivo dessa classe é ler um arquivo único
 * e a partir dele gerar vários fragmentos do arquivo
 * original conforme um tamanho de bloco definido.
 *
 * Os requisitos são utilizar classes e métodos
 * da API NIO para realizar as operações
 * de leitura do arquivo de origem e escrita nos arquivos
 * de destino.
 *
 * Etapas:
 * - Ler arquivo único.
 * - Dividir em blocos menores.
 * - Salvar blocos menores de acordo
 * com o nome do arquivo original e com número sequencial
 * de acordo com a quantidad de fragmentos lidos.
 */
public class FragmentadorDeArquivo {

    private final Path arquivoOrigem;
    private final int tamanhoFragmento;

    public FragmentadorDeArquivo(Path arquivoOrigem, int tamanhoFragmento) {
        Objects.requireNonNull(arquivoOrigem);
        validarTamanhoFragmento(tamanhoFragmento);

        this.arquivoOrigem = arquivoOrigem;
        this.tamanhoFragmento = tamanhoFragmento;
    }

    /**
     * Na solução para fragmentar o arquivo foi utilizado:
     *
     * - Um Channel para ler os blocos do arquivo de origem
     * utilizando a implementação de ByteChannel com opção de leitura.
     *
     * - Um Channel para escrever os blocos lidos a partir do arquivo de origem
     * utilizando a implementação de ByteChannel com opções de criação e escrita.
     */
    public void fragmentar() throws IOException {
        try (ByteChannel canalLeituraArquivoOrigem = Files.newByteChannel(arquivoOrigem, READ)) {
            final ByteBuffer buffer = ByteBuffer.allocate(tamanhoFragmento);

            int quantidadeFragmentosLidos = 0;

            while (canalLeituraArquivoOrigem.read(buffer) > 0) {
                buffer.flip();
                Path arquivoDestino = obterFragmentoArquivo(arquivoOrigem, ++quantidadeFragmentosLidos);
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
        try (ByteChannel canalEscritaArquivoDestino = Files.newByteChannel(arquivoDestino, CREATE_NEW, WRITE)) {
            canalEscritaArquivoDestino.write(buffer);
        }
    }
}
