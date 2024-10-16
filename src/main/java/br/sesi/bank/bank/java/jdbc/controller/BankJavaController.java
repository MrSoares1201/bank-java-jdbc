package br.sesi.bank.bank.java.jdbc.controller;

import br.sesi.bank.bank.java.jdbc.domain.cliente.DadosCadastroCliente;
import br.sesi.bank.bank.java.jdbc.domain.conta.DadosAberturaConta;
import br.sesi.bank.bank.java.jdbc.exceptions.RegraDeNegocioException;
import br.sesi.bank.bank.java.jdbc.service.ContaService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class BankJavaController {
    ContaService service;
    Scanner teclado;
    public Integer numero;
    public DadosCadastroCliente dadosCliente;

    public BankJavaController(){
        this.service = new ContaService();
        this.teclado = new Scanner(System.in).useDelimiter("\n");
    }

    public static void main(String[] args) throws SQLException{
        BankJavaController controller = new BankJavaController();
        controller.start();
    }

    public void start() throws SQLException {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");

        var opcao = exibirMenu();
        while (opcao != 8) {
            try {
                switch (opcao) {
                    case 1:
                        listarContas();
                        break;
                    case 2:
                        abrirContas();
                        break;
                    case 3:
                        encerrarContas();
                        break;
                    case 4:
                        consultarSaldo();
                        break;
                    case 5:
                        realizarSaque();
                        break;
                    case 6:
                        realizarDeposito();
                        break;
                    case 7:
                        realizarTransferencia();
                        break;
                }
            } catch (RegraDeNegocioException e){
                System.out.println("Erro: " +e.getMessage());
                System.out.println("Pressione qualquer tecla e de ENTER para voltar para o menu");
                teclado.next();
            }
            opcao = exibirMenu();
        }

        System.out.println("Finalizado a aplicação.");
    }
    private int exibirMenu(){
        System.out.println("""
              BYTEBANK - ESCOLHA UMA OPÇÃO:
              1 - Listar contas abertas
              2 - Abertura de conta
              3 - Encerramento de conta
              4 - Consultar saldo de uma conta
              5 - Realizar saque em uma conta
              6 - Realizar depósito em uma conta
              7 - Realizar uma transferência
              8 - Sair
              """);
        return teclado.nextInt();
    }
    private void listarContas(){
        System.out.println("Contas cadastradas:");
        var contas = service.listarContasAbertas();
        contas.stream().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principar");
        teclado.next();

    }
    private void abrirContas(){
        System.out.println("Digite o número da conta:");
        var numeroDaConta = teclado.nextInt();

        System.out.println("Digite o nome do cliente:");
        var nome = teclado.next();

        System.out.println("Digite o cpf do cliente");
        var cpf = teclado.next();

        System.out.println("Digite o email do cliente");
        var email = teclado.next();

        service.abrir(new DadosAberturaConta(numeroDaConta, new DadosCadastroCliente(nome, cpf, email)));

        System.out.println("Conta aberta com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }
    private void encerrarContas(){
        System.out.println("Digite o numero da conta");
        var numeroDaConta = teclado.nextInt();

        service.encerrar(numeroDaConta);

        System.out.println("Conta encerrada com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    private void consultarSaldo(){
        System.out.println("Digite o número da conta:");
        var numeroDaConta = teclado.nextInt();
        var saldo = service.consultarSaldo(numeroDaConta);
        System.out.println("Saldo da conta: " +saldo);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal:");
        teclado.next();

    }
    private void realizarSaque(){
        System.out.println("Digite o número da conta");
        var numeroDaConta = teclado.nextInt();

        System.out.println("Digite o valor do saque:");
        var valor = teclado.nextBigDecimal();

        service.realizarSaque(numeroDaConta, valor);
        System.out.println("Depósito realizado com sucesso!");
        System.out.println("Pressiona qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }
    private void realizarDeposito(){
        System.out.println("Digite o número da conta:");
        var numeroDaConta = teclado.nextInt();

        System.out.println("Digite o valor do depósito:");
        var valor = teclado.nextBigDecimal();

        service.realizarDeposito(numeroDaConta, valor);

        System.out.println("Depósito realizado com sucesso!");
        System.out.println("Pressiona qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }
    private void realizarTransferencia(){
        System.out.println("Digite o número da conta de ORIGEM");
        int numeroContaOrigem = teclado.nextInt();
        System.out.println("Digite o número da conta de DESTINO: ");
        int numeroContaDestino = teclado.nextInt();
        System.out.println("Informe o valor a ser transferido:");
        BigDecimal valor = teclado.nextBigDecimal();

        //this.service.realizaTransferencia(numeroContaDestino, numeroContaOrigem, valor);
        //System.out.println("Transferência realizada com sucesso!");
        //System.out.println("Pressiona qualquer tecla e de ENTER para voltar ao menu principal");
        //teclado.next();

    }
}