
import lombok.*;
import models.cliente.Cliente;
import models.conta.*;
import java.util.*;

@Data
@NoArgsConstructor
public class Banco {
    @Getter
    @Setter
    private Set<Conta> contas = new HashSet<>() ;
    private Set<Cliente> clientes = new HashSet<>();
    private Map<Conta, Cliente> clientesContas = new HashMap<>();

    public Cliente criarNovoCliente(String nome, String cpf) {
        if(clientes.stream().anyMatch(cliente -> Objects.equals(cliente.getCpf(), cpf)))
            return null;
        Cliente novoCliente = Cliente.builder().nome(nome).cpf(cpf).build();
        clientes.add(novoCliente);
        return novoCliente;
    }

    public ContaCorrente criarNovaContaCorrente(Integer agencia, Integer numero, Cliente titular){
        if(contas.stream().anyMatch(conta -> Objects.equals(conta.getAgencia(), agencia) && Objects.equals(conta.getNumero(), numero)))
            return null;
        ContaCorrente contaCorrente = new ContaCorrente(agencia, numero, titular);
        contas.add(contaCorrente);
        clientesContas.put(contaCorrente, titular);
        return contaCorrente;
    }

    public ContaPoupanca criarNovaContaPoupanca(Integer agencia, Integer numero, Cliente titular){
        if(contas.stream().anyMatch(conta -> Objects.equals(conta.getAgencia(), agencia) && Objects.equals(conta.getNumero(), numero)))
            return null;
        ContaPoupanca contaPoupanca = new ContaPoupanca(agencia, numero, titular);
        contas.add(contaPoupanca);
        clientesContas.put(contaPoupanca, titular);
        return contaPoupanca;
    }

    public Conta encontrarConta(Integer agencia, Integer numero){
        return contas.stream()
                .filter(conta -> Objects.equals(conta.getAgencia(), agencia) && Objects.equals(conta.getNumero(), numero))
                .findFirst()
                .orElse(null);
    }
}
