package Banco.Agencia.Contas;

import Util.Data;
import Banco.Agencia.Agencia;
import Banco.Agencia.Clientes.Cliente;

public class Corrente extends Conta {

    private float limCheque;
    private float taxaAdmin;

    public Corrente(int nroConta, int senha, float saldo, boolean conjunta,
    Cliente Cliente_primario, Agencia agencia,
    Data aberturaConta) { // Precisa fazer alteracao para Data
        super(nroConta, senha, saldo, conjunta, Cliente_primario, agencia, aberturaConta);
        this.limCheque = 0;
        this.taxaAdmin = 30f;
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