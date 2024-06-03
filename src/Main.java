import models.cliente.Cliente;
import models.conta.Conta;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        menuPrincipal();
    }

    public static void menuPrincipal() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int escolha;
        do{
            System.out.println("| Bem vindo ao seu Banco Digital |");
            System.out.println("| 1 - CRIAR CONTA.               |");
            System.out.println("| 0 - ENCERRAR SESSÃO.           |");
            System.out.print("Operação desejada: ");
            escolha = Integer.parseInt(scan.nextLine());

            switch (escolha) {
                case 1:
                    menuCriarContaCliente();
                    break;
                case 0:
                    System.out.println("ATÉ MAIS.");
                    break;
                default:
                    System.out.println("OPERAÇÃO INVÁLIDA.");
            }
        }while(escolha != 0);
        scan.close();
    }

    public static void menuCriarContaCliente() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        Banco banco = new Banco();
        int agencia, numero;

        System.out.println("\n== CADASTRO DE CLIENTE ==");
        System.out.println("Digite seu NOME: ");  String nome = scan.nextLine();
        System.out.println("Digite seu CPF: ");   String cpf = scan.nextLine();

        Cliente titular = banco.criarNovoCliente(nome, cpf);

        if(!Objects.isNull(titular))                System.out.println("SUCESSO AO CADASTRAR !!");
        else                                        System.out.println("ERRO -> CPF JÁ CADASTRADO.");

        System.out.println("\n== CADASTRO DE CONTA ==");
        System.out.println("SELECIONE O TIPO DE CONTA:");
        System.out.println("1 - CORRENTE");
        System.out.println("2 - POUPANÇA");
        int escolha = Integer.parseInt(scan.nextLine());

        switch (escolha){
            case 1:
                System.out.println("\nAGÊNCIA:  ");     agencia = Integer.parseInt(scan.nextLine());
                System.out.println("NÚMERO:     ");     numero  = Integer.parseInt(scan.nextLine());

                Conta novaCC = banco.criarNovaContaCorrente(agencia, numero, titular);
                if(Objects.isNull(novaCC)) { System.out.println("CONTA JÁ CADASTRADA NO SISTEMA..."); }
                else {
                    System.out.println("SUCESSO AO CADASTRAR !!");
                    menuOperacoesConta(novaCC, banco);
                }
                break;
            case 2:
                System.out.println("\nAGÊNCIA:  ");     agencia = Integer.parseInt(scan.nextLine());
                System.out.println("NÚMERO:     ");     numero  = Integer.parseInt(scan.nextLine());

                Conta novaCP = banco.criarNovaContaPoupanca(agencia, numero, titular);
                if(Objects.isNull(novaCP)) { System.out.println("CONTA JÁ CADASTRADA NO SISTEMA."); }
                else{
                    System.out.println("SUCESSO AO CADASTRAR !!");
                    menuOperacoesConta(novaCP, banco);
                }
                break;
            default:
                System.out.println("OPERAÇÃO INVÁLIDA.");
                break;
        }
    }

    public static void menuOperacoesConta(Conta conta, Banco banco) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int escolha;

        do {
            System.out.printf("== Agencia: %d, Conta: %d ==%n", conta.getAgencia(), conta.getNumero());
            System.out.println("| 1 - DEPOSITAR.                 |");
            System.out.println("| 2 - SACAR.                     |");
            System.out.println("| 3 - TRANSFERIR.                |");
            System.out.println("| 0 - MENU INICIAL.              |");
            System.out.print("Operação desejada: ");
            escolha = Integer.parseInt(scan.nextLine());

            switch (escolha){
                case 1:
                    System.out.println("\n[DEPOSITAR]");
                    System.out.print("Informe o valor para depósito: ");
                    double valorDeposito = Double.parseDouble(scan.nextLine());

                    conta.depositar(valorDeposito);
                    processando();

                    System.out.println("\nSUCESSO !!\nCONFIRA EXTRATO:");
                    conta.imprimirExtrato();
                    processando();
                    break;
                case 2:
                    System.out.println("\n[SACAR]");
                    System.out.print("Informe o valor para saque: ");
                    double valorSaque = Double.parseDouble(scan.nextLine());

                    processando();
                    boolean resultadoDaOperacao = conta.sacar(valorSaque);

                    if(!resultadoDaOperacao){
                        System.out.println("\nERRO -> SALDO ATUAL INSUFICIENTE\nEXTRATO:");
                        conta.imprimirExtrato();
                    }else{
                        System.out.println("\nSUCESSO !!\nCONFIRA EXTRATO:");
                        conta.imprimirExtrato();
                    }
                    processando();
                    break;
                case 3:
                    System.out.println("\n[TRANSFERIR]");
                    System.out.println("DADOS DA CONTA DESTINO");
                    System.out.print("AGÊNCIA: ");      int agencia = Integer.parseInt(scan.nextLine());
                    System.out.print("NÚMERO: ");       int numero = Integer.parseInt(scan.nextLine());

                    Conta contaDestino = banco.encontrarConta(agencia, numero);
                    processando();

                    if(Objects.isNull(contaDestino)){
                        System.out.println("\nERRO -> CONTA NÃO ENCONTRADA.");
                        break;
                    }

                    System.out.print("INFORME O VALOR PARA TRANSFERÊNCIA: ");
                    double valor = Double.parseDouble(scan.nextLine());
                    processando();

                    if(!conta.transferencia(valor, contaDestino)){
                        System.out.println("ERRO -> SALDO INSUFICIENTE.\nEXTRATO: ");
                        conta.imprimirExtrato();
                    }else{
                        System.out.println("SUCESSO AO TRANSFERIR !!\nEXTRATO: ");
                        conta.imprimirExtrato();
                    }
                    break;
                case 0:
                    System.out.println('\n');
                    break;
                default:
                    System.out.println("OPERAÇÃO INVÁLIDA.\n");
                    break;
            }
        }while(escolha != 0);
    }

    public static void processando() throws InterruptedException {
        System.out.print("Processando");
        Thread.sleep(500);
        System.out.print('.');
        Thread.sleep(1000);
        System.out.print('.');
        Thread.sleep(1000);
        System.out.print('.');
        Thread.sleep(500);
        System.out.println('\n');
    }
}
