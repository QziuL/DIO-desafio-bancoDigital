package models.conta;

import models.cliente.Cliente;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(Integer agencia, Integer numero, Cliente titular) {
        super(agencia, numero, titular);
    }

    @Override
    public void imprimirExtrato(){
        System.out.println("=== Extrato da Conta Poupança ===");
        super.imprimirInfosComuns();
    }
}
