package Banco.Agencia.Contas;

import Util.Data;

import Banco.Agencia.Clientes.Cliente;

public class Corrente extends Conta {

    private float limCheque;
    private float taxaAdmin;

    public Corrente(int numConta, int senha, float saldo, boolean conjunta,
            Cliente clientePrimario, int numAgencia,
            Data aberturaConta, float limCheque, float taxaAdmin) {

        super(numConta, senha, saldo, conjunta, clientePrimario, numAgencia, aberturaConta);
        this.tipoConta = "Corrente";
        this.limCheque = 2f;
        this.taxaAdmin = 30f;
    }

    @Override
    public String saidaArquivo() {
        String data = "Salario;" + super.saidaArquivo() + ";" + this.limCheque + ";" + this.taxaAdmin;
        return data;
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
        String data = super.printConta() + this.limCheque + ";" + this.taxaAdmin + ";";

        return data;
    }

}