package Banco.Agencia;

import java.util.*;

import Banco.Agencia.Contas.Conta;
import Banco.Agencia.Clientes.Cliente;
import Banco.Agencia.Funcionarios.Funcionario;
import Banco.Agencia.Funcionarios.Gerente;
import Util.*;

public class Agencia {

    private int numAgencia;
    private String nomeAgencia;
    private Endereco enderecoAgencia;
    private Gerente gerente;
    private LinkedList<Conta> contas;
    private LinkedList<Funcionario> funcionarios;
    // private LinkedList<Pessoa> Funcionarios;
    // private LinkedList<Cliente> clientes;

    public Agencia(String nomeAgencia, int numAgencia) {
        this.nomeAgencia = nomeAgencia;
        this.numAgencia = numAgencia;
        this.contas = new LinkedList<>();
        this.funcionarios = new LinkedList<>();
        // this.clientes = new LinkedList<Cliente>();
    }

    public Agencia(String nomeAgencia, int numAgencia, Endereco enderecoAgencia, Gerente gerente) {
        this.nomeAgencia = nomeAgencia;
        this.numAgencia = numAgencia;
        this.enderecoAgencia = enderecoAgencia;
        this.gerente = gerente;
        gerente.setAgencia(this);
        // gerente.setEstaEmUmaAgencia(true);
        this.contas = new LinkedList<Conta>();
        this.funcionarios = new LinkedList<Funcionario>();
        // this.clientes = new LinkedList<Cliente>();
    }

    /////////////////////////////
    ///// GETTERS E SETTERS /////
    /////////////////////////////

    public int getNumAgencia() {
        return this.numAgencia;
    }

    public String getNomeAgencia() {
        return this.nomeAgencia;
    }

    public Endereco getEnderecoAgencia() {
        return this.enderecoAgencia;
    }

    public Gerente getGerente() {
        return this.gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
        gerente.setAgencia(this);
    }

    public LinkedList<Conta> getContas() {
        return this.contas;
    }

    public LinkedList<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    public void setContas(LinkedList<Conta> contas) {
        this.contas = contas;
    }

    public void setFuncionarios(LinkedList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public void setNumAgencia(int numAgencia) {
        this.numAgencia = numAgencia;
    }

    public void setEnderecoAgencia(Endereco enderecoAgencia) {
        this.enderecoAgencia = enderecoAgencia;
    }

    ///////////////////////
    ///// SAIDA DE DADOS /
    /////////////////////

    public void printNomeLocalizacao() {
        System.out.println(nomeAgencia + ": " + enderecoAgencia.getRua() + ", " + enderecoAgencia.getNumero() + ", "
                + enderecoAgencia.getCidade() + ", " + enderecoAgencia.getEstado() + ", " + enderecoAgencia.getPais()
                + ", " + enderecoAgencia.getCep());
    }

    public void LocalizaAgencia(String Cidade, String Estado) {
        if (this.enderecoAgencia.getCidade().equals(Cidade) && this.enderecoAgencia.getEstado().equals(Estado)) {
            printNomeLocalizacao();
        }
    }

    public void LocalizaAgencia(String Bairro, String Cidade, String Estado) {
        if (this.enderecoAgencia.getBairro() == Bairro && this.enderecoAgencia.getCidade().equals(Cidade)
                && this.enderecoAgencia.getEstado().equals(Estado)) {
            printNomeLocalizacao();
        }
    }

    public void LocalizaAgencia(String Estado) {
        if (this.enderecoAgencia.getEstado().equals(Estado)) {
            printNomeLocalizacao();
        }
    }

    public String printAgencia() {
        String Data = this.nomeAgencia + ";" +
                this.numAgencia + ";" +
                enderecoAgencia.printEndereco();
        return Data;
    }

    /////////////////////////
    ///// FUNCIONÁRIOS /////
    ///////////////////////

    public void removerGerenteAtual() {
        // em
    }

    public void adicionarNovoGerente() {

    }

    public boolean isFuncionarioDaAgencia(Funcionario funcionario) { // Verifica se o funcionário está na agência
        boolean isFuncionario = false;
        for (Funcionario func : this.funcionarios) {
            if (func.equals(funcionario)) {
                isFuncionario = true;
                break;
            }
        }
        return isFuncionario;
    }

    public int encontrarFuncionario(int pos) { // Encontra o funcionário na lista de funcionários
        // int pos = -1;

        // for(Funcionario funcionario : this.funcionarios){
        for (int j = 0; j < funcionarios.size(); j++) {
            // Funcionario atual = funcionario;
            // if(atual.getNome().equals(funcionario.getNome()) &&
            // atual.getCpf().equals(funcionario.getCpf())){
            // pos = this.funcionarios.indexOf(funcionario);
            // break;
            Funcionario atual = (Funcionario) funcionarios.get(j);
            System.out.print(pos + " - ");
            atual.printFunc();
            pos++;
        }
        return pos;
    }

    ///////////////////
    ///// CONTAS /////
    /////////////////

    public Conta encontrarConta(int numConta, int senha) throws IllegalArgumentException {
        for (Conta conta : this.contas) {
            if (conta.getNumConta() == numConta) {
                if (conta.getSenha() != senha) {
                    if (!conta.isEstado()) {
                        throw new IllegalArgumentException("Conta inativa");
                    }
                } else
                    throw new IllegalArgumentException("Senha incorreta");
                // conta.print();
                return conta;
            }
        }
        throw new IllegalArgumentException("Conta não encontrada");
    }

    public boolean enviarTranferencia(int numConta, float valor, int numBancoOrigem, int numAgenciaOrigem,
            int numContaOrigem) {
        for (Conta conta : this.contas) {
            if (conta.getNumConta() == numConta) {
                if (conta.getNumConta() == numConta) {
                    conta.receberTranf(numBancoOrigem, numAgenciaOrigem, numContaOrigem, valor);
                    ;
                    return true;
                }
            }
        }
        return false;
    }

    public void listarContas() {
        for (Conta conta : this.contas) {
            conta.print();
        }
    }

    public void alteraConta(Conta nova) {
        // for(int i = 0; i < this.contas.size(); i++){
        for (Conta contaAtual : this.contas) {

            // contaAtual.getNumConta() == nova.getNumConta()
            if (contaAtual.equals(nova)) {
                // contas.set(i, nova);
                this.contas.remove(contaAtual);
                this.contas.add(nova);
                break;
            }
        }
    }

    //////////////////
    ///// CRIAR /////
    ////////////////

    /*
     * public void criaContaCorrente(Conta contaNova){
     * this.contas.add(contaNova);
     * //contaNova.criaConta(this);
     * }
     * 
     * public void criaContaPoupanca(Conta contaNova){
     * this.contas.add(contaNova);
     * //contaNova.criaConta(this);
     * }
     * 
     * public void criaContaSalario(Conta contaNova){
     * this.contas.add(contaNova);
     * //contaNova.criaConta(this);
     * }
     */
}
