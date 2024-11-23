package com.algaworks.pagamento;

import java.math.BigDecimal;

/**
 * Exemplo de sealed class (classe selada).
 *
 * São utilizadas para prevenir o mau uso de herança,
 * protegendo a hierarquia, evitando que uma implementação não tratada seja criada,
 * onde por exemplo, em alguma lógica que necessite de "instance of" para testar uma variável de interface,
 * realizar cast e chamar operações específicas de uma implementação.
 * Sem sealed classes, uma nova implementação sem os devidos cuidados,
 * não vai ser tratada nos pontos onde necessitar de alteração.
 *
 * É estabelecido definindo-se na classe base a palavra chave sealed.
 *
 * Na classe classe base são definidos os tipos que são permitidos herdá-la (permits),
 * e os subtipos devem ser definidos com final, sealed ou non-sealed.
 *
 * - Definidos com final = fecham a hierarquia a partir deles, não permitindo nenhuma extensão
 * (é o uso que mais faz sentido).
 *
 * - Definidos com sealed = fecham a hierarquia a partir deles, permitindo apenas as classes
 * especificadas realizarem a herança.
 *
 * - Definidos com non-sealed = permitem a herança a partir deles para qualquer subclasse.
 *
 * O uso de sealed classes deve ser sempre analisado, para não se perder o benefício da herança e
 * extensabilidade.
 *
 * O seu uso mais útil e comum se dá com a construção de classes utilitárias, bibliotecas e frameworks,
 * para proteger implementações padrão de comportamentos inesperados.
 */
public sealed abstract class MetodoPagamento permits Pix, CartaoDeCredito {

    private BigDecimal tarifa;

    public MetodoPagamento(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

}