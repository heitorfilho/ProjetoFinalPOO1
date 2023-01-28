package Banco.Agencia.Contas;

import Util.Data;

public class Corrente extends Conta {

    private float limCheque;
    private float taxaAdmin;

    public Corrente(int nroConta, int senha, float saldo, Data aberturaConta, float limCheque,
            float taxaAdmin) { // Precisa fazer alteracao para Data
        super(nroConta, senha, saldo, aberturaConta);
        this.limCheque = limCheque;
        // this.limCheque = 0;
        this.taxaAdmin = taxaAdmin;
        // this.taxaAdmin = 30f;
    }

    public float getLimCheque() {
        return this.limCheque;
    }

    public float getTaxaAdmin() {
        return this.taxaAdmin;
    }

    public void setLimCheque(float limCheque) {
        this.limCheque = limCheque;
    }

    public void setTaxaAdmin(float taxaAdmin) {
        this.taxaAdmin = taxaAdmin;
    }

    public String printConta() {
        super.printConta();
        String data = this.limCheque + ";" + this.taxaAdmin + ";";

        return data;
    }

}