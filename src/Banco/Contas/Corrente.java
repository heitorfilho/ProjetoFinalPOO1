package Banco.Contas;

import Util.Data;

public class Corrente extends Conta{

    private float limiteChequeEspecial;
    private float taxaAdministrativa;

    public Corrente(int nroConta, int senha, float saldo, Data aberturaConta, float limiteChequeEspecial, float taxaAdministrativa){ // Precisa fazer alteracao para Data
        super(nroConta, senha, saldo, aberturaConta);
        this.limiteChequeEspecial = limiteChequeEspecial;
        this.taxaAdministrativa = taxaAdministrativa;
    }

    public float getLimiteChequeEspecial(){
        return this.limiteChequeEspecial;
    }

    public float getTaxaAdministrativa(){
        return this.taxaAdministrativa;
    }

    public void setLimiteChequeEspecial(float limiteChequeEspecial){
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public void setTaxaAdministrativa(float taxaAdministrativa){
        this.taxaAdministrativa = taxaAdministrativa;
    }

    public String printConta(){
        super.printConta();
        String data = this.limiteChequeEspecial + ";" + this.taxaAdministrativa + ";";

        return data;
    }

    
}