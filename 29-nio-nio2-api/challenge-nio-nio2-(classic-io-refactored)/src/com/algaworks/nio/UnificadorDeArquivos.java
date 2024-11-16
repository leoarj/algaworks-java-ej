package com.algaworks.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.*;

/**
 * Solução segue a mesma lógica da classe {@link FragmentadorDeArquivo},
 * porém lendo diversos arquivos de origem para transformar em um único de destino.
 */
public class UnificadorDeArquivos {

    private static final Pattern PADRAO_SEQUENCIA_NOME_ARQUIVO = Pattern.compile("\\.(\\d+)");

    private final Path diretorioFragmentosArquivo;
    private final Path arquivoUnificado;
    private final String prefixoFragmentosArquivo;

    public UnificadorDeArquivos(Path diretorioFragmentosArquivo, Path arquivoUnificado,
                                String prefixoFragmentosArquivo) {
        Objects.requireNonNull(diretorioFragmentosArquivo);
        Objects.requireNonNull(arquivoUnificado);
        validarPrefixoFragmentoArquivo(prefixoFragmentosArquivo);

        this.diretorioFragmentosArquivo = diretorioFragmentosArquivo;
        this.arquivoUnificado = arquivoUnificado;
        this.prefixoFragmentosArquivo = prefixoFragmentosArquivo;
    }

    public void unificar() throws IOException {
        List<Path> fragmentosArquivo = listarFragmentosArquivoOrdenados(diretorioFragmentosArquivo);

        try (final ByteChannel canalEscritaArquivoDestino = Files.newByteChannel(arquivoUnificado, CREATE_NEW, WRITE)) {
            fragmentosArquivo.forEach(
                    fragmentoArquivo -> escreverFragmentoNoArquivoUnificado(fragmentoArquivo, canalEscritaArquivoDestino));
        }
    }

    private static void validarPrefixoFragmentoArquivo(String prefixo) {
        if ((prefixo == null) || prefixo.isBlank()) {
            throw new IllegalArgumentException("Prefixo dos arquivos não pode ser nulo/vazio");
        }
    }

    /**
     * Representa um predicado para dizer se o arquivo é incluso
     * no retorno do filtro ou não.
     */
    private boolean isFragmentoArquivo(Path arquivo) {
        return arquivo.getFileName()
                .toString()
                .contains(prefixoFragmentosArquivo.concat("."));
    }

    /**
     * Representa uma ToIntFunction, que transforma um {@link File} em
     * um valor inteiro a partir de propriedade específica.
     * <br/>
     * @param arquivo Arquivo a ter propriedade específica transformada
     * em um valor inteiro.
     * @return Valor referente a propriedade transformada.
     * @implNote Arquivos devem ser comparados por padrão definido no nome,
     * no caso numeração em sequência presente no nome,
     * porque caso comparar as strings (nome completo) diretamente
     * por ordem natural pode ocasionar uma ordenação diferente
     * da sequêncial (comparando os números sequenciais dos arquivos).
     *
     */
    private int compararFragmentoArquivoPorPadraoNome(Path arquivo) {
        Matcher matcher = PADRAO_SEQUENCIA_NOME_ARQUIVO.matcher(arquivo.getFileName().toString());

        return (matcher.find() ? Integer.parseInt(matcher.group(1)) : 0);
    }

    private List<Path> listarFragmentosArquivoOrdenados(Path diretorio) throws IOException {
        try (var stream = Files.list(diretorio)) {
            return stream.filter(this::isFragmentoArquivo)
                    .sorted(Comparator.comparingInt(this::compararFragmentoArquivoPorPadraoNome))
                    .toList();
        }
    }

    private void escreverFragmentoNoArquivoUnificado(Path fragmentoArquivo,
                                                     final ByteChannel canalEscritaArquivoUnificado) {
        try (ByteChannel canalLeituraArquivoOrigem = Files.newByteChannel(fragmentoArquivo, READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (canalLeituraArquivoOrigem.read(buffer) > 0) {
                buffer.flip();
                canalEscritaArquivoUnificado.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException("Falha ao escrever arquivo fragmentado no arquivo unificado", e);
        }
    }
}
