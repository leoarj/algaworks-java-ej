package com.algaworks.io;

import java.io.*;

/**
 * Objetivo dessa classe é ler um arquivo texto
 * e a partir dele gerar um novo arquivo com o conteúdo em letras maiúsculas.
 */
public class EscritorArquivoMaiusculo {

    private File arquivoOrigem;
    private File arquivoDestino;

    public EscritorArquivoMaiusculo(File arquivoOrigem, File arquivoDestino) {
        this.arquivoOrigem = arquivoOrigem;
        this.arquivoDestino = arquivoDestino;
    }

    /**
     * Na solução para gerar um novo arquivo texto com conteúdo
     * das letras em maísculo foi utilizado:
     *
     * - Um stream de entrada orientado a caracteres para ler as linhas do arquivo de origem
     * utilizando a implementação de reader com buffer (BufferedReader).
     *
     * - Um stream de saída orientado a caracteres para escrever as linhas no arquivo de destino
     * utilizando a implementação de writer com buffer (BufferedWriter).
     */
    public void processar() throws IOException {
        try (BufferedReader leitorArquivo = new BufferedReader(new FileReader(arquivoOrigem));
             BufferedWriter escritorArquivo = new BufferedWriter(new FileWriter(arquivoDestino))) {

            String linha = null;
            while ((linha = leitorArquivo.readLine()) != null) {
                escritorArquivo.write(linha.toUpperCase());
                escritorArquivo.newLine();
            }
        }
    }
}
