package interfaces;

import models.conta.Conta;

public interface IConta {
    public void depositar(double valor);
    public boolean sacar(double valor);
    public boolean transferencia(double valor, Conta conta);
    public void imprimirExtrato();
}
