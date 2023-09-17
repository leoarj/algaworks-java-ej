package com.github.leoarj.algaworks.course.ej.nestedClasses.vendas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/*
* Obs.: Código inicial fornecido em classes aninhadas e depois refatorado para utilizar classes aninhadas.
*
* Classes aninhadas (Nested classes).
*
* São classes declaradas dentro de uma classe superior.
*
* Útil para agrupar classes logicamente, apenas em contextos necessários.
*
* 4 Formas de implementação.
*
* 1 - Classes aninhadas estáticas = Não necessita acessar métodos e atributos de instância da classe superior
* e objetos podem existir independentemente de objetos da classe superior.
* Obs.: Enums declarados dentro de uma classe sempre são static por padrão.
*
* 2 - Classes internas (Inner classes)
*   2.1 - Classes aninhadas não-estáticas = Precisa acessar métodos e atributos de instância da classe superior
* e objetos precisam existir dependendo de objetos da classe superior.
*
*   2.2 - Classes locais = Classes declaradas dentro de um método ou bloco de código como um if e somente utilizada
* no contexto local do método.
* Obs.: Argumentos do método onde a classe local está declarada se tornam atributos de instância
* de uma classe declarada localmente, sendo que o valor dos argumentos podem ser utilizados posteriormente pelo
* objeto instanciado da classe local.
*
*   2.3 - Classes anônimas = Bloco de código que representa a extensão de uma superclasse ou a implementação
* de uma interface, sem nome, apenas sobreescrevendo os métodos necessários, onde a partir daí
* é obtida uma instância de um objeto.
* Com classes anônimas é possível utilizar o operador new com uma interface desde que o bloco
* de código da classe seja declarado posteriormente.
* Exemplo: Runnable runnable = new Runnable() {... implementação dos métodos...};
* Obs.: Também usada localmente em método ou bloco de código menor.
*/

public class Pedido {

    private final Cliente cliente;
    private StatusPedido status = StatusPedido.RASCUNHO;
    BigDecimal valorTotal = BigDecimal.ZERO;
    private final List<ItemPedido> itens = new ArrayList<>();

    public Pedido(Cliente cliente) {
        Objects.requireNonNull(cliente);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public ItemPedido adicionarItem(String descricao, int quantidade, BigDecimal valorUnitario) {
        ItemPedido item = new ItemPedido(descricao, quantidade, valorUnitario);
        itens.add(item);
        return item;
    }

    public void emitir() {
        checarPedidoParaModificacao();
        status = StatusPedido.EMITIDO;
    }

    public void cancelar() {
        checarPedidoParaModificacao();
        status = StatusPedido.CANCELADO;
    }

    void checarPedidoParaModificacao() {
        if (!StatusPedido.RASCUNHO.equals(status)) {
            throw new IllegalArgumentException("Pedido não pode ser modificado");
        }
    }

    // Classe aninhada não-estática
    public class ItemPedido {
        private final String descricao;
        private final BigDecimal valorUnitario;
        private int quantidade;

        ItemPedido(String descricao, int quantidade, BigDecimal valorUnitario) {
            Objects.requireNonNull(descricao);
            Objects.requireNonNull(valorUnitario);

            if (valorUnitario.compareTo(BigDecimal.ZERO) < 1) {
                throw new IllegalArgumentException("Valor unitário deve ser mair que zero");
            }

            this.descricao = descricao;
            this.valorUnitario = valorUnitario;
            setQuantidade(quantidade);
        }

        public String getDescricao() {
            return descricao;
        }

        public BigDecimal getValorUnitario() {
            return valorUnitario;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            checarPedidoParaModificacao();

            if (quantidade < 1) {
                throw new IllegalArgumentException("Quantidade deve ser maior que zero");
            }

            valorTotal = valorTotal.subtract(calcularValorTotal(this.quantidade));
            this.quantidade = quantidade;
            valorTotal = valorTotal.add(calcularValorTotal(quantidade));
        }

        public BigDecimal getValorTotal() {
            return calcularValorTotal(this.quantidade);
        }

        private BigDecimal calcularValorTotal(int quantidade) {
            return valorUnitario.multiply(new BigDecimal(quantidade));
        }

    }

    // Classe aninhada estática (Enums por padrão já são static)
    public enum StatusPedido {
        RASCUNHO, EMITIDO, CANCELADO
    }

    // Classe aninhada estática
    public static class Cliente {

        private String nome;

        public Cliente(String nome) {
            setNome(nome);
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            Objects.requireNonNull(nome);
            this.nome = nome;
        }
    }
}