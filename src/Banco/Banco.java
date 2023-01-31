package Banco;

import java.util.*;
import Banco.Agencia.*;
import Util.*;
import Banco.Agencia.Clientes.Cliente;

public class Banco {
    private LinkedList<Agencia> agencias;
    private LinkedList<Cliente> clientes;
    private String[] admin = { "Admin", "admin" };

    public Banco() {
        this.agencias = new LinkedList<>();
        this.clientes = new LinkedList<>();
    }

    // ------------------------------------------------------------ //
    // ---------------------GETTERS-E-SETTERS--------------------- //
    // ---------------------------------------------------------- //

    public LinkedList<Agencia> getAgencias() {
        return this.agencias;
    }

    public LinkedList<Cliente> getClientes() {
        return this.clientes;
    }

    public void setAgencias(LinkedList<Agencia> agencias) {
        this.agencias = agencias;
    }

    public void setClientes(LinkedList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean loginAdmin(String usuario, String senha) {
        if (usuario.equals(admin[0]) && senha.equals(admin[1])) {
            return true;
        }
        return false;
    }

    // ------------------------------------------------------------ //
    // ---------------------FUNCIONÁRIOS-------------------------- //
    // ---------------------------------------------------------- //

    public void areaDoFuncionario(Scanner scan) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("1 - Entrar no sistema");
            System.out.println("2 - Cadastrar funcionário");
            System.out.println("3 - Promover a gerente");
            System.out.println("4 - Cadastrar nova agência");
            System.out.println("5 - Encontrar um funcionário");
            System.out.println("6 - Lista de clientes");
            System.out.println("7 - Lista de contas");
            System.out.println("8 - Encontrar agências próximas");
            System.out.println("0 - Voltar");

            try {
                opcao = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida!");
                System.out.println("Digite um número de 0 a 8");
                scan.nextLine();
                continue;
            }

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    acessoFuncionario(scan);
                    opcao = 0;
                    break;
                case 2:
                    try{
                        cadastraFuncionario(scan);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao cadastrar funcionário");
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Erro ao cadastrar funcionário");
                        System.out.println(e.getMessage());
                    }
                    opcao = 0;
                    break;
                case 3:
                    promoverGerente(scan);
                    opcao = 0;
                    break;
                case 4:
                    try{
                        cadastraAgencia(scan);
                    } catch (IllegalAccessException e) {
                        System.out.println(e.getMessage());
                    } System.out.println("A nova agência precisa de um gerente");
                    Arquivos.salvarArquivoAgencia(agencias);
                    agencias = Arquivos.carregarAgencias();
                    opcao = 0;
                    break;
                case 5:
                    encontrarFuncionario();
                    opcao = 1;
                    break;
                case 6:
                    listaClientes();
                    opcao = 1;
                    break;
                case 7:
                    listaContas();
                    opcao = 1;
                    break;
                case 8:
                    listaAgencias();
                    opcao = 1;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    System.out.println("Digite um número de 0 a 8");
                    break;

            }

        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////
/*
 * Banco.criaConta(); //Banco.java
 * qual agencia? = 1 //Banco.java
 * conta vai ser oq? = corrente //Banco.java
 * conta cont1; //Banco.java
 * cont1 = new ContaCorrente(); //Banco.java
 * agencia1.criaContaCorrente(cont1); //Banco.java - Agencia.java -
 * ContaCorrente.java
 * 
 * Banco CancoDoHeitor = new Banco();
 */
////////////////////////////////////////////////////////////////////////////////////////
