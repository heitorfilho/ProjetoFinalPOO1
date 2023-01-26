package Banco;

public abstract class Conta {

    private boolean estado;
    private int nroConta;
    private int senha;
    private float saldoAtual;
    // private Data dataAbertura;
    // private Data dataUltimaMovimentacao;

    public Conta(int nroConta, int senha, float saldoAtual) { // Precisa fazer alteracao para Data
        this.estado = true;
        this.nroConta = nroConta;
        this.senha = senha;
        this.saldoAtual = saldoAtual;
        // this.dataAbertura = dataAbertura;
        // this.dataUltimaMovimentacao = dataAbertura;
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

    public float getSaldoAtual() {
        return this.saldoAtual;
    }

    /*
     public Data getDataAbertura(){
     return this.DataAbertura;
     }
     */

    /*
     public Data getUltimaMovimentacao(){
     return this.DataUltimaMovimentacao;
     }
     */

    public void setEstado(boolean estado) {
        this.estado = estado;

    }

    public void setNroConta(int nroConta) {
        this.nroConta = nroConta;

    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public void setSaldoAtual(float saldoAtual) {
        this.saldoAtual = saldoAtual;
    }


}