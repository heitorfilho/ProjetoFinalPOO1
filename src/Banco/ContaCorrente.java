package Banco;

public class ContaCorrente extends Conta{

    private float limiteChequeEspecial;
    private float taxaAdministrativa;

    public ContaCorrente(int nroConta, int senha, float saldoAtual, float limiteChequeEspecial, float taxaAdministrativa){ // Precisa fazer alteracao para Data
        super(nroConta,senha,saldoAtual);
        this.limiteChequeEspecial = limiteChequeEspecial;
        this.taxaAdministrativa = taxaAdministrativa;
    }

    
}