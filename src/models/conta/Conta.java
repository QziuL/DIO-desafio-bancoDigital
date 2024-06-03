package models.conta;

import interfaces.IConta;
import lombok.*;
import models.cliente.Cliente;

@Data
public class Conta implements IConta {
    @Getter
    protected Integer agencia;
    protected Integer numero;
    protected Cliente titular;
    protected Double saldo = 0d;

    public Conta(Integer agencia, Integer numero, Cliente titular) {
        this.agencia = agencia;
        this.numero = numero;
        this.titular = titular;
    }

    @Override
    public void depositar(double valor){
        this.saldo += valor;
    }

    @Override
    public boolean sacar(double valor){
        if(this.saldo < valor) return false;
        this.saldo -= valor;
        return true;
    }

    @Override
    public boolean transferencia(double valor, Conta conta){
        if(this.saldo < valor) return false;
        this.saldo -= valor;
        conta.saldo += valor;
        return true;
    }

    @Override
    public void imprimirExtrato() {}

    public void imprimirInfosComuns(){
        System.out.println("Titular: "+titular.getNome()+"\nAgencia: "+agencia+"\nNumero: "+numero+"\nSaldo: "+saldo);
    }
}
