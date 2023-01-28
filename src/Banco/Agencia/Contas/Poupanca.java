package Banco.Agencia.Contas;

import Util.Data;

public class Poupanca extends Conta{

    private float rendimentoMesAtual;

    public Poupanca(int nroConta, int senha, float saldo, Data aberturaConta){ //Mudar apos atualizar conta
        super(nroConta, senha, saldo, aberturaConta);
        this.rendimentoMesAtual = 0.5f;

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