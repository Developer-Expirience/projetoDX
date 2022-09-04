package org.example;

public class PagarComPix implements Pagavel{
    private String chavePix;
    private String nome;
    private String origem;
    private String destino;
    private Double saldo;
   private Double valor;

    @Override
    public Double pagarCom() {
        return valor;
    }

    public PagarComPix(String chavePix, String nome, String origem, String destino, Double valor, Double saldo) {
        this.chavePix = chavePix;
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.saldo = saldo;
    }

    public String getChavePix() {
        return chavePix;
    }

    public String getNome() {
        return nome;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
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
                "-".repeat(30) + '\n' +
                "Extrato Pix: " + '\n' +
                "chavePix: " + chavePix + '\n' +
                "Nome: " + nome + '\n' +
                "Origem: " + origem + '\n' +
                "Destino: " + destino + '\n' +
                "Valor: " +  pagarCom() + '\n' +
                "Saldo: "+ saldo;
    }
}
