package Banco.Contas;

import Util.Data;
 
public abstract class Conta {
    
    protected int nroConta;
    protected int senha;
    protected float saldo;
    protected boolean estado;
    protected Data aberturaConta;
    protected Data ultimaMovimentacao;

    // protected LinkedList<Movimentacao> movimentacoes;
    // protected Cliente ClientePrimario;
    // protected Cliente ClienteSecundario;
    // protected int numAgencia;

    // private Agencia agencia;

 
    public Conta(int nroConta, int senha, float saldo, Data aberturaConta) {
        this.nroConta = nroConta;
        this.senha = senha;
        this.saldo = saldo;
        this.aberturaConta = aberturaConta;
        this.ultimaMovimentacao = aberturaConta;
        ultimaMovimentacao = new Data(0, 0, 0);
        this.estado = true;

        // this.Movimentacoes = new LinkedList< >();
    }

    public boolean getEstado() {
        return this.estado;
    }

    public int getNroConta() {
        return this.nroConta;
    }

    public int getSenha() {
        return this.senha;
    }

    public float getsaldo() {
        return this.saldo;
    }


     public Data getaberturaConta(){
     return this.aberturaConta;
     }


     public Data getUltimaMovimentacao(){
     return this.ultimaMovimentacao;
     }


    public void setEstado(boolean estado) {
        this.estado = estado;

    }

    public void setNroConta(int nroConta) {
        this.nroConta = nroConta;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public void setsaldo(float saldo) {
        this.saldo = saldo;
    }

    public String printConta(){
        String data = this.estado + ";" + this.nroConta + ";" + this.saldo + ";" + this.aberturaConta.printData() +
        ";" + this.ultimaMovimentacao.printData();

        return data;
    }


}