package Banco.Agencia.Contas;

import Util.Data;

public class Poupanca extends Conta{

    private float rendimentoMesAtual;

    public Poupanca(int nroConta, int senha, float saldo, Data aberturaConta, float rendimentoMesAtual){
        super(nroConta, senha, saldo, aberturaConta);
        this.rendimentoMesAtual = rendimentoMesAtual;

    }

    public float getRendimentoMesAtual(){
        return this.rendimentoMesAtual;
    }

    public void setRendimentoMesAtual(float rendimentoMesAtual){
        this.rendimentoMesAtual = rendimentoMesAtual;
    }

    public String printConta(){
        super.printConta();
        String data = this.rendimentoMesAtual + ";";

        return data;
    }

}