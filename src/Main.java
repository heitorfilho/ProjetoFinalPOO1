import java.util.InputMismatchException;
import java.util.Scanner;

import Banco.Banco;

public class Main {
    public static void main(String[] args) {
        int opcao = 123;
        Scanner scan = new Scanner(System.in);
        String NomeBanco = "Banco do Brasil";
        Banco banco = new Banco();

        System.out.println("Bem vindo ao " + NomeBanco + "!");

        // menu
        while (opcao != 0) {
            banco.carregarBanco();
            System.out.println("Escolha uma opcao:");
            System.out.println("1 - Acessar conta");
            System.out.println("2 - Criar uma conta");
            System.out.println("3 - Area do funcionario");
            System.out.println("4 - Encontrar agencia mais proxima");
            System.out.println("5 - Cadastrar cliente");
            System.out.println("0 - Sair");

            scan = new Scanner(System.in);

            try {
                opcao = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida: " + e.getMessage());
                continue;
            }
            scan.nextLine();
            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1: // acessar conta
                    banco.acessarConta(scan);
                    break;
                case 2: // criar conta
                    try {
                        banco.cadastrarConta(scan);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                    }
                    break;
                case 3: // acessar area do funcionario
                    banco.areaDoFuncionario(scan);
                    break;
                case 4: // encontrar agencia mais proxima
                    banco.encontrarAgenciasProx(scan);
                    break;
                case 5: // cadastrar cliente
                    banco.cadastrarCliente(scan, "0");
                    break;
                default:
                    System.out.println("Essa opcao nao existe!");
                    break;
            }
            banco.salvarBanco();
        }
        scan.close();
    }
}
