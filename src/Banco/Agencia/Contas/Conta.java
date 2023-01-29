package Banco.Agencia.Contas;

import java.util.*;
import Util.*;
import Banco.Agencia.Agencia;
import Banco.Agencia.Clientes.Cliente;

public abstract class Conta {

    protected int nroConta;
    protected int senha;
    protected float saldo;
    protected boolean estado;
    protected Data aberturaConta;
    protected Data ultimaMovimentacao;
    protected boolean conjunta;
    protected Cliente clientePrimario; //protected int cpfClientePrimario
    protected Cliente clienteSecundario; // agencia que a conta pertence
    // protected LinkedList<Movimentacao> movimentacoes;
    private Agencia agencia;

    // Conta unica
    public Conta(int nroConta, int senha, float saldo, boolean conjunta,
            Cliente Cliente_primario, Agencia agencia,
            Data aberturaConta) {
        this.nroConta = nroConta;
        this.senha = senha;
        this.saldo = saldo;
        this.agencia = agencia;
        this.aberturaConta = aberturaConta;
        this.ultimaMovimentacao = aberturaConta;
        this.estado = true;
        this.conjunta = conjunta;
        // this.clientePrimario = clientePrimario;
        // this.clienteSecundario = null;
        ultimaMovimentacao = new Data(0, 0, 0);
        // this.Movimentacoes = new LinkedList< >();
    }

    public Conta() {
        this.estado = true;
    }

    /////////////////////////////
    ///// GETTERS E SETTERS /////
    /////////////////////////////

    public int getNroConta() {
        return this.nroConta;
    }

    public int getSenha() {
        return this.senha;
    }

    public float getSaldo() {
        return this.saldo;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public Data getAberturaConta() {
        return this.aberturaConta;
    }

    public Data getUltimaMovimentacao() {
        return this.ultimaMovimentacao;
    }

    public boolean getConjunta() {
        return this.conjunta;
    }

    public Cliente getClientePrimario() {
        return this.clientePrimario;
    }

    public Agencia getAgencia() {
        return this.agencia;
    }

    public void setNroConta(int nroConta) {
        this.nroConta = nroConta;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setAberturaConta(Data aberturaConta) {
        this.aberturaConta = aberturaConta;
    }

    public void setUltimaMovimentacao(Data ultimaMovimentacao) {
        this.ultimaMovimentacao = ultimaMovimentacao;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public String printConta() {
        String data = this.estado + ";" + this.nroConta + ";" + this.saldo + ";" + this.aberturaConta.printData() +
                ";" + this.ultimaMovimentacao.printData();

        return data;
    }

    //////////////////////////
    //// AVALIAR ACESSO /////
    //// VALIDAR CONTA /////
    //// DESATIVAR CONTA //
    //////////////////////

    public boolean verificarSenha(int senha) {
        if (this.senha == senha) {
            return true;
        } else {
            return false;
        }
    }

    public void verificaConjunta() {
        // if (this.conjunta == true) {
        // if (this.clienteSecundario == null) {
        // this.estado = false;
        // }
        // }
    }

    public void desativarConta(int senha) {
        try (Scanner sc = new Scanner(System.in);) {
            if (verificarSenha(senha) && this.estado == true) {
                System.out.println("Deseja realmente desativar a conta? (S/N)");
                String opcao = sc.nextLine();
                if (opcao.equals("S") || opcao.equals("s")) {
                    this.setEstado(false);
                    System.out.println("Conta desativada com sucesso!");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /////////////////////////////////
    ///// OPERAÇÕES BANCÁRIAS //////
    ///////////////////////////////

    public void sacar(float valor, int senha) throws IllegalArgumentException {
        if (verificarSenha(senha) && this.estado == true) {
            if (saldo < valor)
                throw new IllegalArgumentException("Saldo insuficiente!");
            if (valor < 0)
                throw new IllegalArgumentException("Valor inválido!");
            if (this.saldo >= valor)
                this.saldo -= valor;
            this.ultimaMovimentacao = Data.dataAtual();
            /*
             * Movimentacoes Nova = new Movimentacoes();
             * Nova.sacar(valor);
             * this.Movimentacoes.add(Nova);
             */
            System.out.println("Saque realizado com sucesso!");

        } else if (verificarSenha(senha) == false) {
            throw new IllegalArgumentException("Senha incorreta!");
        } else if (this.estado == false) {
            throw new IllegalArgumentException("Conta desativada!");
        }
    }

    public void depositar(float valor, int senha) throws IllegalArgumentException {
        if (verificarSenha(senha) && this.estado == true) {
            if (valor < 0)
                throw new IllegalArgumentException("Valor inválido!");
            this.saldo += valor;
            this.ultimaMovimentacao = Data.dataAtual();
            /*
             * Movimentacoes Nova = new Movimentacoes();
             * Nova.depositar(valor);
             * this.Movimentacoes.add(Nova);
             */
            System.out.println("Depósito realizado com sucesso!");

        } else if (verificarSenha(senha) == false) {
            throw new IllegalArgumentException("Senha incorreta!");
        } else if (this.estado == false) {
            throw new IllegalArgumentException("Conta desativada!");
        }
    }

    public void consultarSaldo(int senha) throws IllegalArgumentException {
        if (verificarSenha(senha) || this.estado == true) {
            System.out.println(this.getSaldo());
        } else {
            throw new IllegalArgumentException("Senha incorreta!");
        }
    }

    public void efetuarPag(float valor, int senha, String tipo) {
        if (verificarSenha(senha)) {
            if (this.saldo >= valor) {
                this.saldo -= valor;
                this.ultimaMovimentacao = Data.dataAtual();
                /*
                 * Movimentacoes Nova = new Movimentacoes();
                 * Nova.realizarPag(valor, TipoPagamento);
                 * this.Movimentacoes.add(Nova);
                 */
            } else {
                throw new IllegalArgumentException("Saldo insuficiente!");
            }
        } else {
            throw new IllegalArgumentException("Senha incorreta!");
        }
    }

    public void efetuarTransf(int nroBanco, int numeroAgencia, int nroConta, float valor, int senha) {
        if (verificarSenha(senha)) {
            if (valor <= 0) {
                throw new IllegalArgumentException("Valor inválido!");
            }
            if (this.saldo >= valor) {
                this.saldo -= valor;
                this.ultimaMovimentacao = Data.dataAtual();
                /*
                 * Movimentacoes Nova = new Movimentacoes();
                 * Nova.tranferir(valor, Numbanco, numAgencia, NumConta);
                 * this.Movimentacoes.add(Nova);
                 */
            } else {
                throw new IllegalArgumentException("Saldo insuficiente!");
            }
        } else {
            throw new IllegalArgumentException("Senha incorreta!");
        }
    }

    public void receberTranf(int nroBanco, int numeroAgencia, int nroConta, float valor) {
        this.saldo += valor;
        this.ultimaMovimentacao = Data.dataAtual();
        /*
         * Movimentacoes Nova = new Movimentacoes();
         * Nova.ReceberTransferencia(valor, Numbanco, numAgencia, NumConta);
         * this.Movimentacoes.add(Nova);
         */
    }

    ///////////////////////////
    /////// Cria Conta ///////
    /////////////////////////

    public void criaConta(Agencia agenciaConta) {
        try(Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o numero da conta?");
            this.nroConta = sc.nextInt();

            System.out.println("Digite a senha");
            this.senha = sc.nextInt();

            System.out.println("Digite o saldo inicial");
            float novoSaldo;
            novoSaldo = sc.nextFloat();
            if (novoSaldo < 0) {
                this.saldo = 0;
            } else {
                this.saldo = novoSaldo;
            }

            this.aberturaConta = Data.dataAtual();

            this.ultimaMovimentacao = Data.dataAtual();

            System.out.println("Escolha uma opcao: ");
            System.out.println("1-Conta normal");
            System.out.println("2-Conta conjunta");
            int opcaoConjunta = sc.nextInt();
            if (opcaoConjunta == 1) {
                conjunta = false;
            } else if (opcaoConjunta == 2) {
                conjunta = true;
            } else {
                System.out.println("Opcap invalida, a conta sera unitaria");
                conjunta = false;
            }

            if(this.conjunta == true) {
                System.out.println("O primeiro cliente ja existe?");
                System.out.println("1-Sim");
                System.out.println("2-Nao");
                int opcaoCliente = sc.nextInt();
                if (opcaoCliente == 1) {
                    System.out.println("Digite o CPF do cliente");
                    String cpf = sc.next();
                    Cliente cliente = new Cliente();
                    //cliente = cliente.buscaCliente(cpf); Metodo para buscar o cliente no banco de dados
                    this.clientePrimario = cliente;
                } else if (opcaoCliente == 2) {
                    Cliente clientePrimario = new Cliente(); //Atualizar para Cliente(Agencia);
                    //clientePrimario.criaCliente(); Metodo para criar o cliente no banco de dados
                    this.clientePrimario = clientePrimario;
                    
                } else {
                    System.out.println("Opcao invalida, o cliente sera criado");
                }
                System.out.println("O segundo cliente ja existe?");
                System.out.println("1-Sim");
                System.out.println("2-Nao");
                int opcaoCliente2 = sc.nextInt();
                if (opcaoCliente2 == 1) {
                    System.out.println("Digite o CPF do cliente");
                    String cpf = sc.next(); // Busca sera por cpf?
                    Cliente clienteSecundario = new Cliente();
                    //cliente = cliente.buscaCliente(cpf); Metodo para buscar o cliente no banco de dados
                    this.clienteSecundario = clienteSecundario;
                } else if (opcaoCliente2 == 2) {
                    Cliente clienteSecundario = new Cliente(); //Atualizar para Cliente(Agencia);
                    //clienteSecundario.criaCliente(); Metodo para criar o cliente no banco de dados
                    this.clienteSecundario = clienteSecundario;
                    
                } else {
                    System.out.println("Opcao invalida, o cliente sera criado");
                }
                
            }else{
                System.out.println("O primeiro cliente ja existe?");
                System.out.println("1-Sim");
                System.out.println("2-Nao");
                int opcaoCliente = sc.nextInt();
                if (opcaoCliente == 1) {
                    System.out.println("Digite o CPF do cliente");
                    String cpf = sc.next();
                    Cliente cliente = new Cliente();
                    //cliente = cliente.buscaCliente(cpf); Metodo para buscar o cliente no banco de dados
                    this.clientePrimario = cliente;
                } else if (opcaoCliente == 2) {
                    Cliente clientePrimario = new Cliente(); //Atualizar para Cliente(Agencia);
                    //clientePrimario.criaCliente(); Metodo para criar o cliente no banco de dados
                    this.clientePrimario = clientePrimario;
                    
                } else {
                    System.out.println("Opcao invalida, o cliente sera criado");
                }
            }

            this.agencia = agenciaConta;

            //FALTA MOVIMENTACOES//

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    ///////////////////////////
    ///// SAIDA DE DADOS /////
    /////////////////////////

    public String saidaArquivo() {
        String segundoTitularCPF = "";

        if (conjunta)
            segundoTitularCPF = clienteSecundario.getCpf();

        String data = nroConta + ";" + senha + ";" + saldo + ";" + aberturaConta + ";" + ultimaMovimentacao + ";"
                + conjunta + ";" + estado + ";" + clientePrimario.getCpf() + ";" + segundoTitularCPF + ";"
                + aberturaConta.printData() + ";" + ultimaMovimentacao.printData() + ";"
                + ultimaMovimentacao.printData() + ";";

        return data;
    }

    public void print() {
        System.out.println("Numero da conta: " + this.nroConta);
        System.out.println("Saldo: " + this.saldo);
        System.out.println("Data de abertura: " + this.aberturaConta);
        System.out.println("Data da ultima movimentacao: " + this.ultimaMovimentacao);
        System.out.println("Conta conjunta: " + this.conjunta);
        System.out.println("Conta ativa: " + this.estado);
    }

    //////////////////////////////
    ///// SALVAR E CARREGAR /////
    ////////////////////////////

    
}