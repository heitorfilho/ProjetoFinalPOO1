package Banco.Agencia.Contas;

import java.util.*;
import Util.*;
import Util.Exceptions.*;
import Banco.Agencia.Contas.Movimentacoes.*;
import Banco.Clientes.*;

public abstract class Conta {

    protected String tipoConta;
    protected int numConta;
    protected int senha;
    protected float saldo;
    protected boolean estado;
    protected int numAgencia;
    protected boolean conjunta;
    protected Data aberturaConta;
    protected Data ultimaMovimentacao;
    protected Cliente clientePrimario;
    protected Cliente clienteSecundario;
    protected LinkedList<Movimentacao> movimentacoes;

    // Conta unica
    public Conta(int numConta, int senha, float saldo, boolean conjunta,
            Cliente clientePrimario, int numAgencia, Data aberturaConta) {
        this.numConta = numConta;
        this.senha = senha;
        this.saldo = saldo;
        this.aberturaConta = aberturaConta;
        this.ultimaMovimentacao = aberturaConta;
        this.estado = true;
        this.conjunta = conjunta;
        this.clientePrimario = clientePrimario;
        this.clienteSecundario = null;
        ultimaMovimentacao = new Data(0, 0, 0);
        this.movimentacoes = new LinkedList<>();
    }

    public Conta() {
        this.estado = true;
    }

    /////////////////////////////
    ///// GETTERS E SETTERS /////
    /////////////////////////////

    public int getNumConta() {
        return this.numConta;
    }

    public int getSenha() {
        return this.senha;
    }

    public float getSaldo() {
        return this.saldo;
    }

    public boolean isEstado() {
        return this.estado;
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

    public boolean isConjunta() {
        return this.conjunta;
    }

    public boolean getConjunta() {
        return this.conjunta;
    }

    public Cliente getClientePrimario() {
        return this.clientePrimario;
    }

    public Cliente getClienteSecundario() {
        return this.clienteSecundario;
    }

    public int getNumAgencia() {
        return this.numAgencia;
    }

    public LinkedList<Movimentacao> getMovimentacoes() {
        return this.movimentacoes;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
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

    public void setClientePrimario(Cliente clientePrimario) {
        this.clientePrimario = clientePrimario;
    }

    public void setClienteSecundario(Cliente clienteSecundario) {
        this.clienteSecundario = clienteSecundario;
    }

    public void setUltimaMovimentacao(Data ultimaMovimentacao) {
        this.ultimaMovimentacao = ultimaMovimentacao;
    }

    public void setMovimentacoes(LinkedList<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    /*
     * public String printConta() {
     * String data = this.estado + ";" + this.numConta + ";" + this.saldo + ";" +
     * this.conjunta + ";" + this.aberturaConta.printData() +
     * this.clientePrimario + ";" + this.numAgencia + ";" +
     * this.aberturaConta.printData();
     * 
     * return data;
     * }
     */

    public String printConta() {
        String data = this.tipoConta + ";" + this.numConta + ";" + this.senha + ";" + this.saldo + ";" + this.conjunta
                + ";" + this.clientePrimario + ";"
                + this.clienteSecundario + ";" + this.numAgencia + ";" + this.aberturaConta.printData()
                + this.ultimaMovimentacao.printData();

        return data;
    }

    //////////////////////////
    //// AVALIAR ACESSO /////
    //// VALIDAR CONTA /////
    //// DESATIVAR CONTA //
    // ADICIONAR CLIENTE //
    //////////////////////

    public boolean verificarSenha(int senha) {
        if (this.senha == senha) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verificaConjunta() {
        if (this.conjunta == true) {
            return true;
        } else {
            return false;
        }
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

    public void adicionarCliente(Cliente cliente) {
        if (this.conjunta == true) {
            if (this.clientePrimario == null) {
                this.clientePrimario = cliente;
            } else if (this.clienteSecundario == null) {
                this.clienteSecundario = cliente;
            } else {
                System.out.println("Conta conjunta já possui dois clientes!");
            }
        } else {
            System.out.println("Conta nao e conjunta!");
        }
    }

    /////////////////////////////////
    ///// OPERAÇÕES BANCÁRIAS //////
    ///////////////////////////////

    public void sacar(float valor, int senha) throws IllegalArgumentException, SaldoInsuficienteException {
        if (verificarSenha(senha) && this.estado == true) {
            if (saldo < valor)
                throw new SaldoInsuficienteException("Saldo insuficiente!");
            if (valor < 0)
                throw new IllegalArgumentException("Valor invalido!");
            if (this.saldo >= valor)
                this.saldo -= valor;
            this.ultimaMovimentacao = Data.dataAtual();

            Movimentacao Nova = new Movimentacao();
            Nova.saque(valor);
            this.movimentacoes.add(Nova);

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
                throw new IllegalArgumentException("Valor invalido!");
            this.saldo += valor;
            this.ultimaMovimentacao = Data.dataAtual();

            Movimentacao Nova = new Movimentacao();
            Nova.deposito(valor);
            this.movimentacoes.add(Nova);

            System.out.println("Deposito realizado com sucesso!");

        } else if (verificarSenha(senha) == false) {
            throw new IllegalArgumentException("Senha incorreta!");
        } else if (this.estado == false) {
            throw new IllegalArgumentException("Conta desativada!");
        }
    }

    public void consultarSaldo(int senha) throws IllegalArgumentException {
        if (verificarSenha(senha) || this.estado == true) {
            System.out.println("O saldo atual e: " + this.getSaldo());
        } else { // tratar o erro depois
            throw new IllegalArgumentException("Senha incorreta!");
        }
    }

    public void efetuarPag(float valor, int senha) throws SaldoInsuficienteException {
        if (verificarSenha(senha)) {
            if (this.saldo >= valor) {
                this.saldo -= valor;
                this.ultimaMovimentacao = Data.dataAtual();

                Movimentacao Nova = new Movimentacao();
                Nova.pagamento(valor);
                this.movimentacoes.add(Nova);

            } else {
                throw new SaldoInsuficienteException("Saldo insuficiente!");
            }
        } else {
            throw new IllegalArgumentException("Senha incorreta!");
        }
    }

    // OK
    public boolean efetuarTransf(int numBancoDestino, int numAgenciaDestino, int numContaDestino, float valor,
            int senha) throws SaldoInsuficienteException {
        if (verificarSenha(senha)) {
            if (valor <= 0) {
                throw new IllegalArgumentException("Valor invalido!");
            }
            if (this.saldo >= valor) {
                this.saldo -= valor;
                this.ultimaMovimentacao = Data.dataAtual();

                Movimentacao Nova = new Movimentacao();
                Nova.transferencia(valor, numBancoDestino, numAgenciaDestino, numContaDestino);
                this.movimentacoes.add(Nova);
                return true;

            } else {
                throw new SaldoInsuficienteException("Saldo insuficiente!");
            }
        } else {
            return false;
        }
    }

    // OK
    public void receberTranf(int numBancoOrigem, int numAgenciaOrigem, int numContaOrigem, float valor) {
        this.saldo += valor;
        this.ultimaMovimentacao = Data.dataAtual();

        Movimentacao Nova = new Movimentacao();
        Nova.receberTransferencia(valor, numBancoOrigem, numAgenciaOrigem, numContaOrigem);
        this.movimentacoes.add(Nova);

    }

    public void historicoMovimentacoes(int senha) {
        if (verificarSenha(senha)) {
            for (Movimentacao movimentacao : movimentacoes) {
                System.out.println(movimentacao);
            }
        } else {
            throw new IllegalArgumentException("Senha incorreta!");
        }
    }

    ///////////////////////////
    ///// SAIDA DE DADOS /////
    /////////////////////////

    public String saidaArquivo() {
        String segundoTitularCPF = "";

        if (conjunta) // Se a conta for conjunta, o segundo titular sera o cliente secundario
            segundoTitularCPF = clienteSecundario.getCpf();

        String data = numConta + ";" + senha + ";" + saldo + ";" + aberturaConta + ";" + ultimaMovimentacao + ";"
                + conjunta + ";" + estado + ";" + clientePrimario.getCpf() + ";" + segundoTitularCPF + ";"
                + aberturaConta.printData() + ";" + ultimaMovimentacao.printData() + ";"
                + ultimaMovimentacao.printData() + ";";

        return data;
    }

    public void print() {
        System.out.println("Agencia: " + this.numConta + "Conta: " + this.numConta + "Senha: " + this.senha);
    }

    //////////////////////////////
    ///// SALVAR E CARREGAR /////
    ////////////////////////////

    public void CarregarMovimentacoes() {
        this.movimentacoes = Arquivos.carregarMovimentacoes(numConta, numAgencia);
        atualizarContas();
    }

    public void SalvarMovimentacoes() {
        Arquivos.salvarArquivoMovimentacoes(numAgencia, numConta, movimentacoes);
    }

    public void atualizarContas() { // Metodo para atualizar as contas no banco de dados
        this.saldo = 0;
        for (Movimentacao movimentacao : movimentacoes) {
            Movimentacao percorre = movimentacao;

            if (percorre.getTipoTransacao() == "Saque") { // percorre.getTipoTransacao().equals("Saque")
                this.saldo += percorre.getValor();
                this.ultimaMovimentacao = percorre.getDataTransacao();
            } else if (percorre.getTipoTransacao() == "Deposito") {
                this.saldo -= percorre.getValor();
                this.ultimaMovimentacao = percorre.getDataTransacao();
            } else if (percorre.getTipoTransacao() == "Pagamento") {
                this.saldo -= percorre.getValor();
                this.ultimaMovimentacao = percorre.getDataTransacao();
            } else if (percorre.getTipoTransacao() == "Transferencia") {
                this.saldo -= percorre.getValor();
                this.ultimaMovimentacao = percorre.getDataTransacao();
            } else if (percorre.getTipoTransacao() == "Recebimento") {
                this.saldo += percorre.getValor();
                this.ultimaMovimentacao = percorre.getDataTransacao();
            } else if (percorre.getTipoTransacao() == "Rendimento") {
                this.saldo += percorre.getValor();
                this.ultimaMovimentacao = percorre.getDataTransacao();
            } else {
                System.out.println("Erro ao atualizar conta");
            }
        }
    }
}