package Banco;

import java.util.*;
import Banco.Agencia.*;
import Util.*;
import Banco.Agencia.Clientes.*;
import Banco.Agencia.Funcionarios.*;

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
            System.out.println("2 - Cadastrar funcionario");
            System.out.println("3 - Promover a gerente");
            System.out.println("4 - Cadastrar nova agencia");
            System.out.println("5 - Encontrar um funcionario");
            System.out.println("6 - Lista de clientes");
            System.out.println("7 - Lista de contas");
            System.out.println("8 - Encontrar agências proximas");
            System.out.println("0 - Voltar");

            try {
                opcao = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                System.out.println("Digite um numero de 0 a 8");
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
                    try {
                        // cadastraFuncionario(scan);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao cadastrar funcionario");
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Erro ao cadastrar funcionario");
                        System.out.println(e.getMessage());
                    }
                    opcao = 0;
                    break;
                case 3:
                    // promoverGerente(scan);
                    opcao = 0;
                    break;
                case 4:
                    try {
                        cadastrarAgencia(scan);
                    } catch (IllegalAccessException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("A nova agencia precisa de um gerente");
                    Arquivos.salvarArquivoAgencia(agencias);
                    agencias = Arquivos.carregarAgencias();
                    opcao = 0;
                    break;
                case 5:
                    // encontrarFuncionario();
                    opcao = 1;
                    break;
                case 6:
                    // listaClientes();
                    opcao = 1;
                    break;
                case 7:
                    // listaContas();
                    opcao = 1;
                    break;
                case 8:
                    // encontrarAgenciasProx();
                    opcao = 1;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    System.out.println("Digite um numero de 0 a 8");
                    break;

            }

        }
    }

    public void acessoFuncionario(Scanner scan) {
        System.out.println("Digite o nome do funcionario");
        String nome = scan.nextLine();
        System.out.println("Digite a senha do funcionario");
        String senha = scan.nextLine();

        for (Agencia agencia : agencias) {
            for (Pessoa funcionario : agencia.getFuncionarios()) {
                if (funcionario.getNome().equals(nome) && funcionario.getSenha().equals(senha)) {
                    // funcionario.areaDoFuncionario(scan);
                    return;
                }
            }
        }
        System.out.println("Funcionario nao encontrado");
    }

    public Pessoa encontrarFuncionario(Scanner scan) throws IllegalArgumentException {
        try {
            int func = 1; // variável para o funcionario
            for (int i = 0; i < agencias.size(); i++) {
                System.out.print((i + 1) + " - ");
                agencias.get(i).localizaAgencia();
            }

            System.out.println("Qual Agencia?");
            int agc = scan.nextInt() - 1; // variável para a agencia e -1 para começar a contar do 0

            agencias.get(agc).encontrarFuncionario(1); //
            System.out.println("Qual Funcionario?");
            func = scan.nextInt() - 1;

            if (func <= agencias.get(agc).getFuncionarios().size() && agc <= agencias.size()) { // se ambos existem
                return agencias.get(agc).getFuncionarios().get(func);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Opcao invalida!");
        } catch (InputMismatchException e) {
            System.out.println("Valor invalido, digite um numeral!");
        }
        throw new IllegalArgumentException("Funcionario nao encontrado!");

    }

    public void cadastrarFuncionario(Scanner scan) {
        // encontrarAgenciasProx();

        int indice = scan.nextInt() - 1;
        scan.nextLine();

        // Nome
        System.out.printf("Digite o nome do funcionario");
        String nome = scan.nextLine();

        // CPF
        System.out.printf("Digite o CPF do funcionario");
        String cpf = scan.nextLine();
        if (!ValidaCPF.isCPF(cpf)) {
            throw new IllegalArgumentException("CPF invalido!");
        }

        // Dados Pessoais
        System.out.printf("Genero: ");
        String sexo = scan.nextLine();
        System.out.printf("Estado Civil: ");
        String estadoCivil = scan.nextLine();

        // Endereço
        System.out.printf("Endereco\nPais: ");
        String pais = scan.nextLine();
        System.out.printf("Estado: ");
        String estado = scan.nextLine();
        System.out.printf("Eidade: ");
        String cidade = scan.nextLine();
        System.out.printf("Bairro: ");
        String bairro = scan.nextLine();
        System.out.printf("Rua: ");
        String rua = scan.nextLine();
        System.out.printf("Complemento: ");
        String complemento = scan.nextLine();
        System.out.printf("Numero: ");
        int numero = scan.nextInt();
        System.out.printf("CEP: ");
        int cep = scan.nextInt();
        scan.nextLine();

        // RG
        System.out.printf("UF do RG (letras): ");
        String rg_uf = scan.nextLine();
        System.out.printf("Numeros do RG: ");
        int rg_num = scan.nextInt();
        Data dataIngresso = Data.dataAtual();

        // Data de Nascimento
        System.out.printf("Data de Nascimento\nDia: ");
        int dia = scan.nextInt();
        System.out.printf("Mês: ");
        int mes = scan.nextInt();
        System.out.printf("Ano: ");
        int ano = scan.nextInt();

        // Dados Profissionais
        System.out.printf("Cargo: ");
        String cargo = scan.nextLine();
        System.out.printf("Salario: ");
        float salario = scan.nextFloat();
        System.out.printf("Numero da Carteira de Trabalho: ");
        int numCarteiraTrab = scan.nextInt();

        // Criação dos objetos
        Endereco endereco = new Endereco(pais, numero, cidade, bairro, rua, complemento, estado, cep);
        Data dataNascimento = new Data(dia, mes, ano);
        Funcionario novo = new Funcionario(nome, cpf, dataNascimento, endereco, sexo, estadoCivil,
                numCarteiraTrab, cargo, salario, dataIngresso, rg_num, rg_uf);

        // Adiciona o funcionario na agencia
        agencias.get(indice).getFuncionarios().add(novo);
        agencias.get(indice).salvarArquivo();

    }

    private void promoverAGerente(Scanner scan) { // Promove um funcionario a gerente
        System.out.println("Escolha um funcionario");
        try {
            Funcionario funcionarioAtual = (Funcionario) encontrarFuncionario(scan); // Encontra o funcionario atual
            System.out.printf("Possui Formação básica em Gerência? \n1 -> Sim \n2 ou mais -> Não\n");
            int temp = scan.nextInt();

            boolean formacaoBasica;
            if (temp == 1) { // Se o funcionario possui formação básica em gerência
                formacaoBasica = true;
            } else {
                formacaoBasica = false;
            }

            Data dataIngressoGerente = Data.dataAtual();

            String linha = funcionarioAtual.printFuncionario();
            String[] campos = linha.split(";");

            Data dataNascimento = new Data(Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                    Integer.parseInt(campos[4]));

            Data ingresso = new Data(Integer.parseInt(campos[18]), Integer.parseInt(campos[19]),
                    Integer.parseInt(campos[20]));

            Endereco end = new Endereco(campos[5], Integer.parseInt(campos[6]), campos[7], campos[8], campos[9],
                    campos[10], campos[11], Integer.parseInt(campos[12]));

            Gerente novo = new Gerente(campos[0],
                    campos[1], dataNascimento, end, campos[13], campos[14], Integer.parseInt(campos[15]),
                    Float.parseFloat(campos[17]), ingresso, Integer.parseInt(campos[21]), campos[22], formacaoBasica,
                    dataIngressoGerente);

            for (int i = 0; i < agencias.size(); i++) {
                if (agencias.get(i).isFuncionarioDaAgencia(funcionarioAtual)) {
                    agencias.get(i).setGerente(novo, funcionarioAtual);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getSuppressed());
        }
    }

    public void encontrarFunc(int pos) { // Encontra um funcionario com a posição
        System.out.println("N -> Nome, CPF");
        for (Agencia agencia : agencias) {
            agencia.localizaAgencia();
            pos = agencia.encontrarFuncionario(pos);
        }

    }

    public void encontrarFunc() { // Sobrecarga do método anterior, para nao precisar passar a posição
        System.out.println("N -> Nome, CPF");
        for (Agencia agencia : agencias) {
            int pos = 1;
            agencia.localizaAgencia();
            pos = agencia.encontrarFuncionario(pos);
        }
    }

    // ------------------------------------------------------------ //
    // -------------------------AGÊNCIAS-------------------------- //
    // ---------------------------------------------------------- //

    private void cadastrarAgencia(Scanner scan) throws IllegalAccessException {

        System.out.println("Acesso permitido apenas para administradores!\nPor favor, faca o login:");
        System.out.print("Usuario: ");
        String Usuario = scan.nextLine();
        System.out.print("Senha: ");
        String Senha = scan.nextLine();

        // Verifica se o usuário é um administrador
        boolean acessoAdmin = loginAdmin(Usuario, Senha);
        if (!acessoAdmin)
            throw new IllegalAccessException("Acesso Negado!");

        System.out.print("Nome da Agencia: ");
        String nomeAgencia = scan.nextLine();
        Agencia nova = new Agencia(nomeAgencia, (agencias.size() + 100));
        System.out.print("Pais: ");
        String pais = scan.nextLine();
        System.out.print("Estado: ");
        String estado = scan.nextLine();
        System.out.printf("Cidade: ");
        String cidade = scan.nextLine();
        System.out.printf("Bairro: ");
        String bairro = scan.nextLine();
        System.out.printf("Rua: ");
        String rua = scan.nextLine();
        System.out.printf("Complemento: ");
        String complemento = scan.nextLine();
        System.out.printf("Numero: ");
        int numero = scan.nextInt();
        System.out.printf("CEP: ");
        int cep = scan.nextInt();
        scan.nextLine();

        nova.setEnderecoAgencia(new Endereco(rua, numero, bairro, cidade, estado, pais, complemento, cep));

        System.out.println("Agencia cadastrada com sucesso!");
        agencias.add(nova);

        Arquivos.salvarArquivoAgencia(agencias);
    }

    public void encontrarAgenciasProx(Scanner Scan) {
        int opcao = 1;
        String estado;
        String cidade;
        String bairro;

        while (opcao != 0) {
            System.out.println("Deseja buscar por: ");
            System.out.println("1 - estado");
            System.out.println("2 - cidade e estado");
            System.out.println("3 - bairro, cidade e estado");
            System.out.println("4 - Mostrar todas");
            System.out.println("0 - Voltar");
            try {
                opcao = Scan.nextInt();
                Scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero de 0 a 4");
                break;
            }
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    System.out.println("Qual o estado?");
                    estado = Scan.nextLine();
                    encontrarAgenciasProx(estado);
                    opcao = 0;
                    break;
                case 2:
                    System.out.println("Qual o estado?");
                    estado = Scan.nextLine();
                    System.out.println("Qual a cidade?");
                    cidade = Scan.nextLine();
                    encontrarAgenciasProx(cidade, estado);
                    opcao = 0;
                    break;
                case 3:
                    System.out.println("Qual o estado?");
                    estado = Scan.nextLine();
                    System.out.println("Qual a cidade?");
                    cidade = Scan.nextLine();
                    System.out.println("Qual bairro?");
                    bairro = Scan.nextLine();
                    encontrarAgenciasProx(bairro, cidade, estado);
                    opcao = 0;
                    break;
                case 4:
                    encontrarAgenciasProx();
                    opcao = 0;
                    break;
                default:
                    System.out.println("Opcao inavlida, tente novamente");
                    break;

            }
        }

    }

    // Métodos para encontrar agências proximas, com sobrecarga dos parâmetros
    private void encontrarAgenciasProx(String bairro, String cidade, String estado) {
        for (Agencia agencia : agencias) {
            agencia.localizaAgencia(bairro, cidade, estado);
        }
    }

    private void encontrarAgenciasProx(String cidade, String estado) {
        for (Agencia agencia : agencias) {
            agencia.localizaAgencia(cidade, estado);
        }
    }

    private void encontrarAgenciasProx(String estado) {
        for (Agencia agencia : agencias) {
            agencia.localizaAgencia(estado);
        }
    }

    public void encontrarAgenciasProx() {
        for (Agencia agencia : agencias) {
            agencia.localizaAgencia();
        }
    }

    public int indiceAgencia(Scanner scan) { // informa as agencias disponiveis e retorna a escolhar
        int numAgencia = 0;

        while (true) { // Encontrar o indice da Agencia;
            System.out.println("Digite a agencia que deseja: ");
            encontrarAgenciasProx();

            numAgencia = scan.nextInt() - 1;
            if (numAgencia > -1 && numAgencia < agencias.size()) { // Verifica se o indice é valido;
                break;
            } else
                System.out.println("Opcao indisponivel, tente novamente");
                break;
        }
        return numAgencia;
    }

    public int indiceAgencia(int numAgencia) {
        for (Agencia agencia : agencias) {
            if (agencia.getNumAgencia() == numAgencia) { // Verifica se o indice é valido;
                return agencias.indexOf(agencia);
            }
        }
        return -1;
    }

    public int encontraNumAgencia(Scanner scan) { // retorna o numero da agencia escolhida
        int numAgencia = indiceAgencia(scan) + 99;
        return numAgencia;
    }

    // ------------------------------------------------------------ //
    // -------------------------CLIENTES-------------------------- //
    // ---------------------------------------------------------- //

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
