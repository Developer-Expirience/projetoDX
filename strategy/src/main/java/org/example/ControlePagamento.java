package org.example;

import java.util.ArrayList;
import java.util.List;

public class ControlePagamento {
    List<Pagavel> pagavel = new ArrayList<>();

    public void adicionar(Pagavel metodoPagamento){
        pagavel.add(metodoPagamento);
    }

    public List<Pagavel> exibirPagamento(){
        return pagavel;
    }

    public double pagarComDebito(PagaComDebito debito){
        return debito.setSaldo(debito.getSaldo() - debito.pagarCom());
    }
    public double pagarComPix(PagarComPix pix){
        return pix.setSaldo(pix.getSaldo() - pix.pagarCom());
    }
    public double pagarComCredito(PagaComCredito credito){
        return credito.setSaldo(credito.getSaldo() - credito.pagarCom());
    }
}
