package Banco.Agencia.Contas;

import Banco.Agencia.Clientes.Cliente;
import Util.Data;

public class Salario extends Conta {

    private float limiteSaque;
    private float limiteTransferencia;

    public Salario(int numConta, int senha, float saldo, boolean conjunta,
            Cliente clientePrimario, int numAgencia,
            Data aberturaConta, float limiteSaque, float limiteTransferencia) {
        super(numConta, senha, saldo, conjunta, clientePrimario, numAgencia, aberturaConta);
        this.tipoConta = "Salario";
        this.limiteSaque = limiteSaque;
        this.limiteTransferencia = limiteTransferencia;
    }

    @Override
    public String saidaArquivo() {
        return "Salario;" + super.saidaArquivo() + ";" + this.limiteSaque + ";" + this.limiteTransferencia;
    }

    public float getLimiteSaque(int senha) throws IllegalArgumentException {
        if (this.senha == senha)
            return this.limiteSaque;
        throw new IllegalArgumentException("Senha incorreta");

    }

    public void setLimiteChequeEspecial(float limiteSaque) {
        this.limiteSaque = limiteSaque;
    }

    public float getLimiteTransferencia(int senha) throws IllegalArgumentException {
        if (this.senha == senha)
            return this.limiteTransferencia;
        throw new IllegalArgumentException("Senha incorreta");
    }

    public void setLimiteTransferencia(float limiteTransferencia) {
        this.limiteTransferencia = limiteTransferencia;
    }

    public String printConta() {
        String data = super.printConta() + this.limiteSaque + ";" + this.limiteTransferencia + ";";

        return data;
    }

}