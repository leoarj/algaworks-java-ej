package com.algaworks.io;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Solução segue a mesma lógica da classe {@link FragmentadorDeArquivo},
 * porém lendo diversos arquivos de origem para transformar em um único de destino.
 */
public class UnificadorDeArquivos {

    private static final Pattern PADRAO_SEQUENCIA_NOME_ARQUIVO = Pattern.compile("\\.(\\d+)");

    private File diretorioArquivosFragmentados;
    private File arquivoUnificado;
    private String prefixoArquivosFragmentados;

    public UnificadorDeArquivos(File diretorioArquivosFragmentados, File arquivoUnificado,
                                String prefixoArquivosFragmentados) {
        Objects.requireNonNull(diretorioArquivosFragmentados);
        Objects.requireNonNull(arquivoUnificado);
        validarPrefixoArquivosFragmentados(prefixoArquivosFragmentados);

        this.diretorioArquivosFragmentados = diretorioArquivosFragmentados;
        this.arquivoUnificado = arquivoUnificado;
        this.prefixoArquivosFragmentados = prefixoArquivosFragmentados;
    }

    private static void validarPrefixoArquivosFragmentados(String prefixo) {
        if ((prefixo == null) || prefixo.isBlank()) {
            throw new IllegalArgumentException("Prefixo dos arquivos não pode ser nulo/vazio");
        }
    }

    /**
     * Representa um predicado para dizer se o arquivo é incluso
     * no retorno do filtro ou não.
     */
    private boolean filtrarArquivoFragmentado(File arquivo) {
        return arquivo.getName().contains(prefixoArquivosFragmentados.concat("."));
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
    private int compararArquivoPorPadraoNome(File arquivo) {
        Matcher matcher = PADRAO_SEQUENCIA_NOME_ARQUIVO.matcher(arquivo.getName());

        return (matcher.find() ? Integer.parseInt(matcher.group(1)) : 0);
    }

    private List<File> obterArquivosFragmentadosList(File diretorio) {
        return Arrays.asList(diretorio.listFiles(this::filtrarArquivoFragmentado))
                .stream()
                .sorted(Comparator.comparingInt(this::compararArquivoPorPadraoNome))
                .toList();
    }

    private void escreverArquivoFragmentadoNoArquivoUnificado(File arquivoFragmentado,
                                                              OutputStream escritorArquivoUnificado,
                                                              byte[] fragmento) {
        try (BufferedInputStream leitorArquivoOrigem = new BufferedInputStream(
                new FileInputStream(arquivoFragmentado))) {

            int bytesLidos = 0;
            while ((bytesLidos = leitorArquivoOrigem.read(fragmento)) > 0) {
                escritorArquivoUnificado.write(fragmento, 0, bytesLidos);
            }
        } catch (IOException e) {
            throw new RuntimeException("Falha ao escrever arquivo fragmentado no arquivo unificado", e);
        }
    }

    public void unificar() throws IOException {
        List<File> arquivosFragmentados = obterArquivosFragmentadosList(diretorioArquivosFragmentados);

        try (BufferedOutputStream escritorArquivoDestino = new BufferedOutputStream(
                new FileOutputStream(arquivoUnificado))) {

            byte[] fragmento = new byte[1024];

            arquivosFragmentados.forEach(
                    arquivo -> escreverArquivoFragmentadoNoArquivoUnificado(arquivo, escritorArquivoDestino, fragmento));
        }
    }
}
