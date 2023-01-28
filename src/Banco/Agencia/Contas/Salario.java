package Banco.Agencia.Contas;

import Banco.Agencia.Agencia;
import Banco.Agencia.Clientes.Cliente;
import Util.Data;

public class Salario extends Conta {

    private float limiteSaque;
    private float limiteTransferencia;

    public Salario(int nroConta, int senha, float saldo, boolean conjunta, Cliente cliente_primario, Agencia agencia,
            Data aberturaConta, float limiteSaque, float limiteTransferencia) {
        super(nroConta, senha, saldo, conjunta, cliente_primario, agencia, aberturaConta);
        this.limiteSaque = limiteSaque;
        this.limiteTransferencia = limiteTransferencia;
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
        super.printConta();
        String data = this.limiteSaque + ";" + this.limiteTransferencia + ";";

        return data;
    }

}