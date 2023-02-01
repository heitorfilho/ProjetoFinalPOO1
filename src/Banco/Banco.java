package Banco;

import java.util.*;
import Banco.Agencia.*;
import Util.*;
import Util.Exceptions.*;
import Banco.Agencia.Contas.*;
import Banco.Agencia.Funcionarios.*;
import Banco.Clientes.*;

public class Banco implements ValidaCPF {
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
            System.out.println("\n1 - Entrar no sistema");
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
                        cadastrarFuncionario(scan);
                    } catch (ParametroInvalidoException e) {
                        System.out.println("Erro ao cadastrar funcionario");
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Erro ao cadastrar funcionario");
                        System.out.println(e.getMessage());
                    }
                    opcao = 0;
                    break;
                case 3:
                    promoverAGerente(scan);
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
                    encontrarFunc();
                    opcao = 1;
                    break;
                case 6:
                    listarClientes();
                    opcao = 1;
                    break;
                case 7:
                    listarContas();
                    opcao = 1;
                    break;
                case 8:
                    encontrarAgenciasProx();
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
        System.out.println("Acesso ao sistema esta em manutencao no momento, tente novamente mais tarde");
    }

    public Pessoa encontrarFuncionario(Scanner scan) throws ParametroInvalidoException {
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
        throw new ParametroInvalidoException("Funcionario nao encontrado!");

    }

    public void cadastrarFuncionario(Scanner scan) {
        encontrarAgenciasProx();

        int indice = scan.nextInt() - 1;
        scan.nextLine();

        // Nome
        System.out.printf("Digite o nome do funcionario: ");
        String nome = scan.nextLine();

        // CPF
        System.out.printf("Digite o CPF do funcionario: ");
        String cpf = scan.nextLine();
        if (!ValidaCPF.isCPF(cpf)) {
            throw new ParametroInvalidoException("CPF invalido!");
        }

        // Dados Pessoais
        System.out.printf("\nDados Pessoais");
        System.out.printf("\nGenero: ");
        String sexo = scan.nextLine();
        System.out.printf("Estado Civil: ");
        String estadoCivil = scan.nextLine();

        // Endereço
        System.out.printf("\nEndereco\nPais: ");
        String pais = scan.nextLine();
        System.out.printf("Estado: ");
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

        // RG
        System.out.printf("UF do RG (letras): ");
        String rg_uf = scan.nextLine();
        System.out.printf("Numeros do RG: ");
        int rg_num = scan.nextInt();
        Data dataIngresso = Data.dataAtual();

        // Data de Nascimento
        System.out.printf("\nData de Nascimento\nDia: ");
        int dia = scan.nextInt();
        System.out.printf("Mês: ");
        int mes = scan.nextInt();
        System.out.printf("Ano: ");
        int ano = scan.nextInt();

        // Dados Profissionais
        System.out.printf("Cargo: ");
        String cargo = scan.nextLine();
        cargo = scan.nextLine();
        System.out.printf("Salario: ");
        float salario = scan.nextFloat();
        System.out.printf("Numero da Carteira de Trabalho: ");
        int numCarteiraTrab = scan.nextInt();

        // Criaçao dos objetos
        Endereco endereco = new Endereco(pais, numero, cidade, bairro, rua, complemento, estado, cep);
        Data dataNascimento = new Data(dia, mes, ano);
        Funcionario novo = new Funcionario(nome, cpf, dataNascimento, endereco, sexo, estadoCivil,
                numCarteiraTrab, cargo, salario, dataIngresso, rg_num, rg_uf);

        // Adiciona o funcionario na agencia
        System.out.println("Funcionario cadastrado com sucesso!");
        agencias.get(indice).getFuncionarios().add(novo);
        agencias.get(indice).salvarArquivo();

    }

    private void promoverAGerente(Scanner scan) { // Promove um funcionario a gerente
        System.out.println("Escolha um funcionario");
        try {
            Funcionario funcionarioAtual = (Funcionario) encontrarFuncionario(scan); // Encontra o funcionario atual
            System.out.println("Possui Formaçao básica em Gerência? \n1 -> Sim \n2 ou mais -> Nao");
            int temp = scan.nextInt();

            boolean formacaoBasica;
            if (temp == 1) { // Se o funcionario possui formaçao básica em gerência
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
                    System.out.println("Funcionario promovido a gerente!");
                }
            }
        } catch (ParametroInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getSuppressed());
        }
    }

    public void encontrarFunc(int pos) { // Encontra um funcionario com a posiçao
        System.out.println("N -> Nome, CPF");
        for (Agencia agencia : agencias) {
            agencia.localizaAgencia();
            pos = agencia.encontrarFuncionario(pos);
        }

    }

    public void encontrarFunc() { // Sobrecarga do método anterior, para nao precisar passar a posiçao
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

    public void cadastrarAgencia(Scanner scan) throws IllegalAccessException {

        System.out.println("Acesso permitido apenas para administradores!\nPor favor, faca o login:");
        System.out.print("Usuario: ");
        String Usuario = scan.nextLine();
        System.out.print("Senha: ");
        String Senha = scan.nextLine();

        // Verifica se o usuário é um administrador
        boolean acessoAdmin = loginAdmin(Usuario, Senha);
        // if (!acessoAdmin)
        // throw new IllegalAccessException("Acesso Negado!");

        System.out.print("Nome da Agencia: ");
        String nomeAgencia = scan.nextLine();
        Agencia nova = new Agencia(nomeAgencia, (agencias.size() + 1));
        System.out.print("Pais: ");
        String pais = scan.nextLine();
        System.out.print("Estado: ");
        String estado = scan.nextLine();
        System.out.print("Cidade: ");
        String cidade = scan.nextLine();
        System.out.print("Bairro: ");
        String bairro = scan.nextLine();
        System.out.print("Rua: ");
        String rua = scan.nextLine();
        System.out.print("Complemento: ");
        String complemento = scan.nextLine();
        System.out.print("Numero: ");
        int numero = scan.nextInt();
        System.out.print("CEP: ");
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

    public int indiceAgencia(Scanner scan) { // informa as agencias disponiveis e retorna a escolhida
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

    public void cadastrarCliente(Scanner scan, String cpf) {
        int opcao = 3;

        while (opcao != 0) {
            try {
                System.out.println("Vamos fazer seu cadastro!");
                if (cpf.equals("0")) { // Se o cpf for 0, o cliente vai digitar o cpf
                    System.out.printf("Digite seu CPF: ");
                    cpf = scan.nextLine();
                }
                boolean cpfValido = ValidaCPF.isCPF(cpf);

                if (!cpfValido) { // Se o cpf for invalido, o cliente tem 3 tentativas
                    opcao--;
                    System.out.println("CPF invalido, voce tem mais " + opcao + " tentativas");
                    cpf = "0";
                } else {
                    Cliente novoCliente = new Cliente();
                    for (Cliente cliente : clientes) { // Verifica se o cliente ja esta cadastrado
                        if (cliente.getCpf().equals(cpf)) {
                            System.out.println("Cliente ja cadastrado");
                            return;
                        }
                    }

                    // Para cpf nao cadastrado, cadastra o cliente:

                    // Dados pessoais
                    System.out.printf("Digite seu nome: ");
                    novoCliente.setNome(scan.nextLine());
                    System.out.printf("Digite seu genero: ");
                    novoCliente.setSexo(scan.nextLine());
                    System.out.printf("Digite seu estado civil: ");
                    novoCliente.setEstadoCivil(scan.nextLine());
                    System.out.printf("Digite sua escolaridade: ");
                    novoCliente.setEscolaridade(scan.nextLine());

                    // Endereco
                    System.out.printf("Endereco");
                    System.out.printf("\nRua: ");
                    String rua = scan.nextLine();
                    System.out.printf("Bairro: ");
                    String bairro = scan.nextLine();
                    System.out.printf("Cidade: ");
                    String cidade = scan.nextLine();
                    System.out.printf("Estado: ");
                    String estado = scan.nextLine();
                    System.out.printf("Pais: ");
                    String pais = scan.nextLine();
                    System.out.printf("Complemento: ");
                    String complemento = scan.nextLine();
                    System.out.printf("Numero: ");
                    int numero = scan.nextInt();
                    System.out.printf("CEP: ");
                    int cep = scan.nextInt();

                    // Data de nascimento
                    System.out.printf("Data de nascimento");
                    System.out.printf("\nDia: ");
                    int dia = scan.nextInt();
                    System.out.printf("Mes: ");
                    int mes = scan.nextInt();
                    System.out.printf("Ano: ");
                    int ano = scan.nextInt();
                    scan.nextLine();

                    // Cadastra o cliente com os dados informados
                    novoCliente.setCpf(cpf);
                    novoCliente.setDataNascimento(dia, mes, ano);
                    Endereco endNovoCliente = new Endereco(rua, numero, bairro, cidade, estado, pais, complemento, cep);
                    novoCliente.setEndereco(endNovoCliente);
                    this.clientes.add((Cliente) novoCliente); // Cast de Pessoa para Cliente
                    opcao = 0;

                }

            } catch (NumberFormatException e) {
                opcao--;
                System.out.println(e + "voce tem mais " + opcao + " Tentativas");
            } catch (InputMismatchException e) {
                opcao--;
                System.out.println("Digite valores validos " + opcao + " Tentativas");
                continue; // Volta para o inicio do while
            }

        }
        System.out.println("Cliente cadastrado com sucesso!");
        Arquivos.salvarArquivoCliente(clientes);
    }

    public int encontrarCliente(String CPF) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(CPF)) {
                return clientes.indexOf(cliente);
            }
        }
        return -1;
    }

    public int indiceCliente(Scanner scan) {

        // Encotrar o cliente existente no banco
        System.out.printf("Digite o seu CPF: ");
        String CPF = scan.nextLine();
        System.out.printf("Confirme o seu CPF: ");
        CPF = scan.nextLine();
        if (!ValidaCPF.isCPF(CPF)) {
            throw new ParametroInvalidoException("CPF invalido");
        }
        int indiceCliente = encontrarCliente(CPF);

        // Cadastro do cliente caso nao exista no banco e retorna o indice dele
        while (indiceCliente == -1) {
            cadastrarCliente(scan, CPF);
            indiceCliente = encontrarCliente(CPF);
        }
        return indiceCliente;
    }

    public void listarClientes() { // Lista todos os clientes cadastrados
        for (Cliente cliente : clientes) {
            System.out.println(cliente.printCliente());
        }
    }

    // ------------------------------------------------------------ //
    // -------------------------CONTAS---------------------------- //
    // ---------------------------------------------------------- //

    public void acessarConta(Scanner scan) { // Acessa a conta do cliente
        int opcao = 1;
        Conta solicitada = loginConta(scan);

        if (solicitada == null) {
            System.out.println("Conta nao encontrada");
            return;
            
        } else if (solicitada.getEstado() != true) { // Verifica se a conta esta ativa ou nao
            System.out.println("Conta inativa");

            System.out.println("Deseja acessar o historico de movimentacoes da conta? 1-Sim 2-Nao");
            int acessoConta = scan.nextInt();

            if (acessoConta == 1) { // Se o usuario desejar acessar o historico de movimentacoes
                System.out.println("Digite a senha da conta: ");
                int senha = scan.nextInt();
                solicitada.historicoMovimentacoes(senha);
            } 
            return;
        } else { // Se a conta estiver ativa
            

            while (opcao != 0) {
                System.out.println("O que voce deseja fazer?");
                System.out.println("1 - Consultar saldo");
                System.out.println("2 - Depositar");
                System.out.println("3 - Sacar");
                System.out.println("4 - Realizar pagamento");
                System.out.println("5 - Transferir");
                System.out.println("6 - Historico bancario");
                System.out.println("0 - Sair");

                opcao = scan.nextInt();
                scan.nextLine();
                boolean acesso = true;

                // variaveis para operacoes
                int numBanco = 0;
                int numAgencia = 0;
                int numConta = 0;
                int senha = 0;
                float valor = 0f;

                try {
                    switch (opcao) {
                        case 0:
                            System.out.println("Saindo...");
                            acesso = false;
                            break;
                        case 1:
                            senha = 0;
                            System.out.println("Saldo: R$" + solicitada.getSaldo());
                            break;
                        case 2:
                            System.out.printf("Digite o valor a ser depositado: R$");
                            valor = scan.nextFloat();
                            System.out.printf("Digite a senha: ");
                            senha = scan.nextInt();
                            solicitada.depositar(valor, senha);
                            break;
                        case 3:
                            System.out.printf("Digite o valor a ser sacado: R$");
                            valor = scan.nextFloat();
                            System.out.printf("Digite a senha: ");
                            senha = scan.nextInt();
                            solicitada.sacar(valor, senha);
                            break;
                        case 4:
                            System.out.printf("Digite o valor a ser pago: R$");
                            valor = scan.nextFloat();
                            System.out.printf("Digite a senha: ");
                            senha = scan.nextInt();
                            solicitada.efetuarPag(valor, senha);
                            break;
                        case 5:
                            try {
                                while (acesso) {
                                    System.out.printf("Digite o valor a ser transferido: R$");
                                    valor = scan.nextFloat();

                                    if (valor > solicitada.getSaldo() || valor <= 0) {
                                        throw new ParametroInvalidoException("Saldo insuficiente");
                                    }
                                    System.out.print("Numero da agencia de destino: ");
                                    numAgencia = scan.nextInt();
                                    System.out.print("Numero da conta de destino: ");
                                    numConta = scan.nextInt();
                                    System.out.print("Senha: ");
                                    senha = scan.nextInt();
                                }
                                acesso = enviarTransferencia(0, numAgencia, numConta, valor, solicitada.getNumAgencia(),
                                        solicitada.getNumConta());

                                if (acesso) { // Se a conta de destino existir e a senha estiver correta
                                    solicitada.efetuarTransf(numBanco, numAgencia, numConta, valor, senha);
                                    agencias.get(solicitada.getNumAgencia() - 100).alteraConta(solicitada);
                                } else
                                    System.out.println("Conta de destino nao encontrada ou senha incorreta");
                            } catch (ParametroInvalidoException e) {
                                System.out.println(e.getMessage());
                                acesso = true;
                            } catch (InputMismatchException e) {
                                System.err.println("Digite valores validos"); // System.err: system.out para erros
                            }
                            break;
                        case 6:
                            System.out.printf("Digite a senha: ");
                            senha = scan.nextInt();
                            System.out.printf("Historico completo:");
                            solicitada.historicoMovimentacoes(senha);
                            break;
                        default:
                            System.out.println(
                                    "Essa opcao nao existe\nEscolha uma das opcoes acima e digite o numero correspondente");
                            opcao = 999;
                            break;
                    }
                } catch (ParametroInvalidoException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.err.println("Digite valores validos"); // System.err: Variacao de system.out para erros
                }

                if (opcao == 0) {
                    return; // Sai do metodo
                }
                System.out.println("Digite 0 para sair ou qualquer outro numero para continuar");
                opcao = scan.nextInt();
            }
        }

    }

    // Cadastra uma conta para o cliente ou retorna a conta ja existente
    public void cadastrarConta(Scanner scan) {
        boolean teste = true;
        int indiceCliente = -1;

        while (teste) {
            try {
                indiceCliente = indiceCliente(scan);
                teste = false;
            } catch (ParametroInvalidoException e) {
                System.out.println(e.getMessage());
                teste = true;
            }
        }

        int indiceAgencia = indiceAgencia(scan); // indice da agencia que contem a conta solicitada

        teste = true;
        Random random = new Random();
        int numConta = 0;

        while (teste) {
            // random.nextInt((max - min) + 1) + min;
            numConta = random.nextInt((999999999 - 111111111) + 1) + 111111111; // gera um numero aleatorio de 9 digitos

            try {
                LinkedList<Conta> percorre = agencias.get(indiceAgencia).getContas();
                for (Conta conta : percorre) {
                    if (conta.getNumConta() == numConta) { // se o numero da conta ja existir, gera outro numero
                        break;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Agencia nao encontrada");
                teste = true;
            }
            teste = false;
        }
        System.out.printf("Numero da conta: " + numConta);
        System.out.printf("\nDigite a senha da conta (apenas numeros): ");
        int senha = scan.nextInt();

        System.out.println("Escolha uma opcao: ");
        System.out.println("1 - Conta Normal");
        System.out.println("2 - Conta Conjunta");
        int opcao = scan.nextInt();

        boolean conjunta;
        if (opcao == 1) {
            conjunta = false;
        } else {
            conjunta = true;
        }

        Data data = Data.dataAtual(); // Registra a data de criacao da conta

        System.out.println("Digite o tipo de conta: ");
        System.out.println("1 - Conta Corrente\n2 - Conta Poupanca\n3 - Salario");
        opcao = scan.nextInt();

        int numAgencia = agencias.get(indiceAgencia).getNumAgencia(); // numero da agencia que contem a conta solicitada

        Conta nova = null;
        switch (opcao) {
            case 1:
                nova = new Corrente(numConta, senha, 0, conjunta, clientes.get(indiceCliente), numAgencia, data, 0, 25);
                break;
            case 2:
                nova = new Poupanca(numConta, senha, 0, conjunta, clientes.get(indiceCliente), numAgencia, data, 0);
                break;
            case 3:
                nova = new Salario(numConta, senha, 0, conjunta, clientes.get(indiceCliente), numAgencia, data, 1000,
                        1000);
                break;
            default:
                System.out.println("Opcao invalida");
                break;
        }

        if (conjunta) { // Caso a conta seja conjunta, cadastra o segundo titular
            try {
                nova.setClienteSecundario(cadastrarSegundoTitular(scan));
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }

        }
        // Adiciona a conta a lista de contas da agencia

        LinkedList<Conta> atualizaContas = agencias.get(indiceAgencia).getContas();
        atualizaContas.add(nova);
        Arquivos.salvarArquivoConta(numAgencia, atualizaContas);
        System.out.println("Conta cadastrada com sucesso");

    }

    public Conta loginConta(Scanner scan) {
        Conta solicitada = null;
        boolean acesso = true;
        while (acesso) {
            acesso = false;

            try {
                System.out.printf("Bem-vindo ao login de conta");
                System.out.printf("Agencia: ");
                int numAgencia = scan.nextInt();
                System.out.printf("Conta: ");
                int numConta = scan.nextInt();
                scan.nextLine();
                System.out.printf("Senha: ");
                int senha = scan.nextInt();

                int indiceAgencia = indiceAgencia(numAgencia); // indice da agencia que contem a conta solicitada

                if (indiceAgencia == -1) {
                    System.out.println("Agencia nao encontrada");
                    acesso = true;
                    break; // Sai do while
                }

                try {
                    solicitada = agencias.get(indiceAgencia).encontrarConta(numConta, senha); // Conta solicitada
                } catch (ParametroInvalidoException e) {
                    System.out.println(e.getMessage());
                    acesso = true;
                    break; // Sai do while
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas numeros");
                scan.nextLine();
                acesso = true;
                break; // Sai do while
            }
        }
        return solicitada;
    }

    public void listarContas() {
        for (Agencia agencia : agencias) {
            agencia.listarContas();
        }
    }

    public Cliente cadastrarSegundoTitular(Scanner scan) {
        int indiceCliente = indiceCliente(scan);
        return clientes.get(indiceCliente);
    }

    // Antigo
    public boolean enviarTransferencia(int numBancoDestino, int numAgenciaDestino, int numContaDestino, Float valor,
            int numAgenciaOrigem, int numContaOrigem) {
        for (Agencia agencia : agencias) {
            if (agencia.getNumAgencia() == numAgenciaOrigem) { // se a agencia de origem for a mesma da agencia que esta
                                                               // sendo percorrida
                return agencia.enviarTransferencia(numContaDestino, valor, numBancoDestino, numAgenciaDestino,
                        numContaOrigem);
            }
        }
        return false;
    }

    // falta numBanco em Banco.java e Agencia.java
    // Novo
    public boolean enviarTransferencia(Conta contOrigem, Conta contDestino, float valor) { // Envia Transferencias entre
                                                                                           // contas do mesmo banco

        if (contOrigem == contDestino) {
            return false;
        } else if (contOrigem.getNumAgencia() == contDestino.getNumAgencia()) { // contas da mesma agencia
            for (Agencia agencia : agencias) {
                if (agencia.getNumAgencia() == contOrigem.getNumAgencia()) { // se a agencia de origem for a mesma da
                                                                             // agencia que esta sendo percorrida
                    agencia.enviarTransferencia(1, contDestino.getNumAgencia(), contDestino.getNumConta(),
                            contOrigem.getNumConta(), valor, contOrigem.getSenha());
                    agencia.receberTransferencia(1, contOrigem.getNumAgencia(), contOrigem.getNumConta(),
                            contDestino.getNumConta(), valor);
                }
            }
            return true;
        } else if (contOrigem.getNumAgencia() != contDestino.getNumAgencia()) {
            for (Agencia agencia : agencias) {
                if (agencia.getNumAgencia() == contOrigem.getNumAgencia()) { // se a agencia de origem for a mesma da
                                                                             // agencia que esta sendo percorrida
                    agencia.enviarTransferencia(1, contDestino.getNumAgencia(), contDestino.getNumConta(),
                            contOrigem.getNumConta(), valor, contOrigem.getSenha());
                } else if (agencia.getNumAgencia() == contDestino.getNumAgencia()) {
                    agencia.receberTransferencia(1, contOrigem.getNumAgencia(), contOrigem.getNumConta(),
                            contDestino.getNumConta(), valor);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // ------------------------------------------------------------ //
    // ------------------SALVAR-E-CARREGAR-ARQUIVOS--------------- //
    // ---------------------------------------------------------- //

    public void carregarBanco() {
        this.agencias = Arquivos.carregarAgencias();
        this.clientes = Arquivos.carregarClientes();
        for (int i = 0; i < agencias.size(); i++) {
            agencias.get(i).carregarArquivos(clientes);
        }
    }

    public void salvarBanco() {
        Arquivos.salvarArquivoAgencia(agencias);
        Arquivos.salvarArquivoCliente(clientes);
        for (int i = 0; i < agencias.size(); i++) {
            agencias.get(i).salvarArquivo();
        }
    }

}
