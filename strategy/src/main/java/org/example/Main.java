package org.example;

public class Main {
    public static void main(String[] args) {
        PagaComCredito credito = new PagaComCredito("a", "22", "157", "08/29", 21022.0, 30000.0);
        PagaComDebito debito = new PagaComDebito("b", "34", "123", "06/27", 12000.0, 34000.0);
        PagarComPix pix = new PagarComPix("12312", "f", "A", "B", 14000.0, 30000.0);

        ControlePagamento pagamento = new ControlePagamento();
        pagamento.adicionar(credito);
        pagamento.adicionar(debito);
        pagamento.adicionar(pix);
        System.out.println(pagamento.exibirPagamento());
        System.out.println("-".repeat(30));
        System.out.println("Novo Extrato");
        pagamento.pagarComDebito(debito);
        pagamento.pagarComPix(pix);
        pagamento.pagarComCredito(credito);
        System.out.println(pagamento.exibirPagamento());
    }
}