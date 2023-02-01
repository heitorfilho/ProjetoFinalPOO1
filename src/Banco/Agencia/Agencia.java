package Banco.Agencia;

import java.util.*;

import Banco.Agencia.Contas.*;
import Banco.Agencia.Funcionarios.*;
import Banco.Clientes.*;
import Util.*;

public class Agencia {

    private int numAgencia;
    private String nomeAgencia;
    private Endereco enderecoAgencia;
    private Gerente gerente;
    private LinkedList<Conta> contas;
    private LinkedList<Pessoa> funcionarios;

    public Agencia(String nomeAgencia, int numAgencia) {
        this.nomeAgencia = nomeAgencia;
        this.numAgencia = numAgencia;
        this.contas = new LinkedList<>();
        this.funcionarios = new LinkedList<>();
    }

    public Agencia(String nomeAgencia, int numAgencia, Endereco enderecoAgencia, Gerente gerente) {
        this.nomeAgencia = nomeAgencia;
        this.numAgencia = numAgencia;
        this.enderecoAgencia = enderecoAgencia;
        this.gerente = gerente;
        gerente.setNumAgencia(this.numAgencia);
        gerente.setEstaEmUmaAgencia(true);
        contas = new LinkedList<Conta>();
        funcionarios = new LinkedList<Pessoa>();
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

    public void setGerente(Gerente novo, Funcionario antigoCargo) {
        RemoverGerenteAtual();
        adicionarNovoGerente(novo, antigoCargo);
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

    public void localizaAgencia() { // localiza a agencia sem parametros, imprime todos os dados
        System.out.println(nomeAgencia + ": " + enderecoAgencia.getRua() + ", " + enderecoAgencia.getNumero() + ", "
                + enderecoAgencia.getCidade() + ", " + enderecoAgencia.getEstado() + ", " + enderecoAgencia.getPais()
                + ", " + enderecoAgencia.getCep());
    }

    public void localizaAgencia(String Bairro, String Cidade, String Estado) { // localiza por bairro, cidade e estado
        if (this.enderecoAgencia.getBairro() == Bairro && this.enderecoAgencia.getCidade().equals(Cidade)
                && this.enderecoAgencia.getEstado().equals(Estado)) {
            localizaAgencia();
        }
    }

    public void localizaAgencia(String Cidade, String Estado) { // sobrecarga, apenas com a cidade e o estado
        if (this.enderecoAgencia.getCidade().equals(Cidade) && this.enderecoAgencia.getEstado().equals(Estado)) {
            localizaAgencia();
        }
    }

    public void localizaAgencia(String Estado) { // sobrecarga do metodo localizaAgencia, apenas com o estado
        if (this.enderecoAgencia.getEstado().equals(Estado)) {
            localizaAgencia();
        }
    }

    public String printAgencia() {
        String Data = this.nomeAgencia + ";" + this.numAgencia + ";" + enderecoAgencia.printEndereco();
        return Data;
    }

    //////////////////////////////////
    //// SALVAR E CARREGAR DADOS ////
    ///////////////////////////////

    public void carregarArquivos(LinkedList<Cliente> clientes) {
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
                continue; // Nao e gerente entao nao faz nada e continua o loop
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

    public void adicionarNovoGerente(Gerente novo, Funcionario AntigoCargo) { // Adiciona um novo gerente na agencia
        for (int j = 0; j < funcionarios.size(); j++) {
            Funcionario TempFunc = (Funcionario) funcionarios.get(j);
            if (TempFunc.equals(AntigoCargo)) {
                funcionarios.remove(j);
                funcionarios.add(j, novo);
                novo.setNumAgencia(this.numAgencia);
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
                        percorre.setNumAgencia(0);
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
                if (conta.verificarSenha(senha)) {
                    if (!conta.isEstado()) {
                        throw new IllegalArgumentException("Conta bloqueada");
                    } else {
                        return conta;
                    }
                } else {
                    throw new IllegalArgumentException("Senha incorreta");
                }
            }
        }
        throw new IllegalArgumentException("Conta nao encontrada");
    }

    public Conta encontrarConta(int numConta) throws IllegalArgumentException {
        for (Conta conta : this.contas) {
            if (conta.getNumConta() == numConta) {
                return conta;
            }
        }
        throw new IllegalArgumentException("Conta nao encontrada");
    }

    // Antigo
    public boolean enviarTransferencia(int numConta, float valor, int numBanco, int numAgencia,
            int numContaOrigem) {
        for (Conta conta : this.contas) {
            if (conta.getNumConta() == numConta) {
                if (conta.getNumConta() == numConta) {
                    conta.receberTranf(numBanco, numAgencia, numContaOrigem, valor);
                    ;
                    return true;
                }
            }
        }
        return false;
    }

    // ENVIAR TRANSFERENCIA

    public boolean enviarTransferencia(int numBancoDestino, int numAgenciaDestino, int numContaDestino,
            int numContaOrigem, float valor, int senha) {
        for (Conta conta : this.contas) {
            if (conta.getNumConta() == numContaOrigem) {
                conta.efetuarTransf(numBancoDestino, numAgenciaDestino, numContaDestino, valor, senha);
                return true;
            }
        }

        return false;
    }

    // RECEBER TRANSFERENCIA

    public boolean receberTransferencia(int numBancoOrigem, int numAgenciaOrigem, int numContaOrigem,
            int numContaDestino, float valor) {
        for (Conta conta : this.contas) {
            if (conta.getNumConta() == numContaDestino) {
                conta.receberTranf(numBancoOrigem, numAgenciaOrigem, numContaOrigem, valor);
                return true;
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
        for (Conta contaAtual : this.contas) {
            if (contaAtual.equals(nova)) {
                this.contas.remove(contaAtual);
                this.contas.add(nova);
                break;
            }
        }
    }

}
