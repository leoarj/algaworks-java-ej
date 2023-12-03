package com.algaworks.io;

import java.io.*;
import java.util.Objects;

/**
 * Objetivo dessa classe é ler um arquivo único
 * e a partir dele gerar vários fragmentos do arquivo
 * original conforme um tamanho de bloco definido.
 *
 * Os requisitos são utilizar classes e métodos
 * da API clássfica de IO para realizar as operações
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

    private File arquivoOrigem;
    private int tamanhoFragmento;

    public FragmentadorDeArquivo(File arquivoOrigem, int tamanhoFragmento) {
        Objects.requireNonNull(arquivoOrigem);
        validarTamanhoFragmento(tamanhoFragmento);

        this.arquivoOrigem = arquivoOrigem;
        this.tamanhoFragmento = tamanhoFragmento;
    }

    private static void validarTamanhoFragmento(int tamanhoFragmento) {
        if (tamanhoFragmento < 1) {
            throw new IllegalArgumentException("Tamanho do fragmento inválido");
        }
    }

    private File obterFragmentoArquivo(File arquivoOrigem, int sequencia) {
        return new File(arquivoOrigem.getPath()
                .concat(".")
                .concat(String.valueOf(sequencia)));
    }

    private void escreverFragmentoArquivo(File arquivoDestino, byte[] fragmento, int quantidadeBytes) throws IOException {
        try (BufferedOutputStream escritorArquivoDestino = new BufferedOutputStream(
                new FileOutputStream(arquivoDestino))) {
            escritorArquivoDestino.write(fragmento, 0, quantidadeBytes);
        }
    }

    /**
     * Na solução para fragmentar o arquivo foi utilizado:
     *
     * - Um stream de entrada para ler os blocos do arquivo de origem
     * utilizando a implementação de input stream com buffer (BufferedInputStream).
     *
     * - Um stream de saída para escrever os blocos lidos a partir do arquivo de origem
     * utilizando a implementação de output stream com buffer (BufferedOutputStream).
     */
    public void fragmentar() throws IOException {
        try (BufferedInputStream leitorArquivoOrigem = new BufferedInputStream(
                new FileInputStream(arquivoOrigem))) {

            byte[] fragmento = new byte[tamanhoFragmento];
            int quantidadeFragmentosLidos = 0;

            int bytesLidos = 0;
            while ((bytesLidos = leitorArquivoOrigem.read(fragmento)) > 0) {
                File arquivoDestino = obterFragmentoArquivo(arquivoOrigem, ++quantidadeFragmentosLidos);

                escreverFragmentoArquivo(arquivoDestino, fragmento, bytesLidos);
            }
        }
    }
}
