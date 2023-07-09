package com.github.leoarj.algaworks.course.ej.exceptions;

public class ContaCorrente {

    private String numero;
    private double saldo;
    private boolean ativa;

    public ContaCorrente(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public boolean isInativa() {
        return !isAtiva();
    }

    public void ativar() {
        this.ativa = true;
    }

    public void inativar() {
        this.ativa = false;
    }

    public boolean sacar(double valor) throws ContaCorrenteSacarException, ContaCorrenteSaldoException,
            ContaCorrenteInativaException {
        if (valor <= 0) {
//            System.out.println("Valor de saque deve ser maior que 0");
//            return false;
            throw new ContaCorrenteSacarException("Valor de saque deve ser maior que 0");
        }

        if (valor > this.saldo) {
//            System.out.println("Conta sem saldo");
//            return false;
            throw new ContaCorrenteSaldoException("Conta sem saldo");
        }

        if (isInativa()) {
//            System.out.println("Conta inativa");
//            return false;
            throw new ContaCorrenteInativaException("Conta inativa");
        }

        this.saldo -= valor;
        return true;
    }

    public boolean depositar(double valor) throws ContaCorrenteDepositarException, ContaCorrenteInativaException {
        if (valor <= 0) {
//            System.out.println("Valor de depósito deve ser maior que 0");
//            return false;
            throw new ContaCorrenteDepositarException("Valor de depósito deve ser maior que 0");
        }

        if (isInativa()) {
//            System.out.println("Conta inativa");
//            return false;
            throw new ContaCorrenteInativaException("Conta inativa");
        }

        this.saldo += valor;
        return true;
    }

    public boolean transferir(ContaCorrente contaDestino, double valor) throws ContaCorrenteInativaException,
            ContaCorrenteTransferirException {
        if (contaDestino.isInativa()) {
//            System.out.println("Conta de destino está inativa");
//            return false;
            throw new ContaCorrenteInativaException("Conta de destino está inativa");
        }

        try {
            if (sacar(valor)) {
                contaDestino.depositar(valor);
                return true;
            }
        } catch (ContaCorrenteException e) {
            // Capturando outras exceções checadas, e lançando nova exceção.
            // Boa prática: Passar a causa da exceção ao propagar uma nova exceção.
            throw new ContaCorrenteTransferirException("Não foi possível realizar a transferência", e);
        }

        return false;
    }

}
