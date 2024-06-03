package models.conta;

import models.cliente.Cliente;

public class ContaCorrente extends Conta {

    public ContaCorrente(Integer agencia, Integer numero, Cliente titular) {
        super(agencia, numero, titular);
    }

    @Override
    public void imprimirExtrato(){
        System.out.println("=== Extrato da Conta Corrente ===");
        super.imprimirInfosComuns();
    }
}
