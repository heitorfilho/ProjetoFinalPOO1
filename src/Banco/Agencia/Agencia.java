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
    private LinkedList<Pessoa> funcionarios;
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
        gerente.setAgencia(this.numAgencia);
        // gerente.setEstaEmUmaAgencia(true);
        this.contas = new LinkedList<Conta>();
        this.funcionarios = new LinkedList<Pessoa>();
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

    public LinkedList<Conta> getContas() {
        return this.contas;
    }

    public LinkedList<Pessoa> getFuncionarios() {
        return this.funcionarios;
    }

    public void setContas(LinkedList<Conta> contas) {
        this.contas = contas;
    }

    public void setFuncionarios(LinkedList<Pessoa> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void setGerente(Gerente novo, Funcionario AntigoCargo) {
        RemoverGerenteAtual();
        adicionarNovoGerente(novo, AntigoCargo);
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

    //////////////////////////////////
    //// SALVAR E CARREGAR DADOS ////
    ///////////////////////////////

    public void CarregarArquivos(LinkedList<Cliente> clientes) {
        carregarContas(clientes);
        carregarFuncionario();
    }

    public void carregarFuncionario() {
        this.funcionarios = Arquivos.carregarFuncionarios(this.numAgencia);
        for (int i = 0; i < funcionarios.size(); i++) {
            try {
                Gerente encontra = (Gerente) funcionarios.get(i);
                if (encontra.getCargo().equals("Gerente")) {
                    this.gerente = encontra;
                }
            } catch (ClassCastException e) {
                continue; // Não é gerente então não faz nada e continua o loop
            }
        }
    }

    public void carregarContas(LinkedList<Cliente> Clientes) {
        this.contas = Arquivos.carregarContas(this.numAgencia, Clientes);
        for (int i = 0; i < contas.size(); i++) {
            contas.get(i).CarregarMovimentacoes();
        }
    }

    public void salvarArquivo() {
        Arquivos.salvarArquivoConta(numAgencia, contas);
        for (int i = 0; i < contas.size(); i++) {
            contas.get(i).SalvarMovimentacoes();
        }
        Arquivos.salvarArquivoFuncionarios(numAgencia, funcionarios);
    }

    /////////////////////////
    ///// FUNCIONÁRIOS /////
    ///////////////////////

    public void adicionarNovoGerente(Gerente novo, Funcionario AntigoCargo) { // Adiciona um novo gerente na agência
        for (int j = 0; j < funcionarios.size(); j++) {
            Funcionario TempFunc = (Funcionario) funcionarios.get(j);
            if (TempFunc.equals(AntigoCargo)) {
                funcionarios.remove(j);
                funcionarios.add(j, novo);
                novo.setAgencia(this.numAgencia);
                novo.setEstaEmUmaAgencia(true);
                this.gerente = novo;
                break;
            }
        }
        Arquivos.salvarArquivoFuncionarios(numAgencia, funcionarios);
    }

    public void RemoverGerenteAtual() {
        if (this.gerente != null) {
            for (int i = 0; i < funcionarios.size(); i++) {
                try {
                    Gerente percorre = (Gerente) funcionarios.get(i);
                    if (percorre.getCpf().equals(this.gerente.getCpf())) {
                        percorre.setAgencia(0);
                        percorre.setEstaEmUmaAgencia(false);
                        percorre.setCargo("Antigo Gerente");
                        funcionarios.remove(i);
                        funcionarios.add(i, percorre);
                    }
                } catch (ClassCastException e) {
                    continue;
                }
            }
            Arquivos.salvarArquivoFuncionarios(numAgencia, funcionarios);
        }
    }

    public boolean isFuncionarioDaAgencia(Funcionario procurado) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).equals(procurado)) {
                return true;
            }
        }
        return false;
    }

    public int encontrarFuncionario(int pos) {
        for (int j = 0; j < funcionarios.size(); j++) {
            Funcionario Atual = (Funcionario) funcionarios.get(j);
            System.out.print(pos + " - ");
            Atual.printFunc();
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
