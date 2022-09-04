package org.example;

public class PagaComCredito implements Pagavel{
    private String nome;
    private String numero;
    private String cvv;
    private String dataValidade;
    private Double saldo;
    private Double valor;

    public PagaComCredito(String nome, String numero, String cvv, String dataValidade, Double valor, Double saldo) {
        this.nome = nome;
        this.numero = numero;
        this.cvv = cvv;
        this.dataValidade = dataValidade;
        this.valor = valor;
        this.saldo = saldo;
    }

    @Override
    public Double pagarCom() {
        return valor * 1.06;
    }

    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    public String getCvv() {
        return cvv;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public double getValor() {
        return valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public double setSaldo(double saldo) {
        this.saldo = saldo;
        return saldo;
    }

    @Override
    public String toString() {
        return  '\n' +
                "-".repeat(30) + '\n'+
                "Extrato com Credito" + '\n' +
                "Nome: " + nome + '\n' +
                "Numero: " + numero + '\n' +
                "cvv: " + cvv + '\n' +
                "Data de Validade: " + dataValidade + '\n' +
                "Valor: " + pagarCom() + '\n' +
                "Saldo: " + saldo;
    }
}
