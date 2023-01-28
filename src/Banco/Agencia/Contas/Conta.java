package Banco.Agencia.Contas;

import java.util.*;
import Util.*;

public abstract class Conta {

    protected int nroConta;
    protected int senha;
    protected float saldo;
    protected boolean estado;
    protected Data aberturaConta;
    protected Data ultimaMovimentacao;
    // protected boolean conjunta; conta conjunta
    // protected Cliente clientePrimario;
    // protected Cliente clienteSecundario;
    // protected int nroAgencia; // agencia que a conta pertence
    // protected LinkedList<Movimentacao> movimentacoes;
    // private Agencia agencia;

    // Conta única
    public Conta(int nroConta, int senha, float saldo, /*
                                                        * boolean conjunta, Clientes Cliente_primario, int Num_Agencia,
                                                        */ Data aberturaConta) {
        this.nroConta = nroConta;
        this.senha = senha;
        this.saldo = saldo;
        this.aberturaConta = aberturaConta;
        this.ultimaMovimentacao = aberturaConta;
        this.estado = true;
        // this.conjunta = conjunta;
        // this.clientePrimario = clientePrimario;
        // this.clienteSecundario = null;
        ultimaMovimentacao = new Data(0, 0, 0);
        // this.Movimentacoes = new LinkedList< >();
    }

    ////////////////////////////////////////////
    ///// GETTERS E SETTERS /////
    //////////////////////////////////////////

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
        Scanner sc = new Scanner(System.in);
        if (verificarSenha(senha) && this.estado == true) {
            System.out.println("Deseja realmente desativar a conta? (S/N)");
            String opcao = sc.nextLine();
            if (opcao.equals("S") || opcao.equals("s")) {
                this.setEstado(false);
                System.out.println("Conta desativada com sucesso!");
            }
            sc.close();
        }
    }

    /////////////////////////////////
    ///// TRANSAÇÕES BANCÁRIAS /////
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
            System.out.println("Depósito realizado com sucesso!");
            
        } else if (verificarSenha(senha) == false) {
            throw new IllegalArgumentException("Senha incorreta!");
        } else if (this.estado == false) {
            throw new IllegalArgumentException("Conta desativada!");
        }
    }

    
    
}