package Banco.Agencia.Contas;

import Banco.Clientes.Cliente;
import Util.Data;
import Util.Exceptions.ParametroInvalidoException;

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

    public Salario(int numConta, int senha, float saldo, boolean conjunta,
            Cliente clientePrimario, int numAgencia,
            Data aberturaConta, float limiteSaque, float limiteTransferencia, Cliente clienteSecundario) {
        super(numConta, senha, saldo, conjunta, clientePrimario, numAgencia, aberturaConta, clienteSecundario);
        this.tipoConta = "Salario";
        this.limiteSaque = limiteSaque;
        this.limiteTransferencia = limiteTransferencia;
    }

    @Override
    public String saidaArquivo() {
        return "Salario;" + super.saidaArquivo() + ";" + this.limiteSaque + ";" + this.limiteTransferencia;
    }

    public float getLimiteSaque(int senha) throws ParametroInvalidoException {
        if (this.senha == senha)
            return this.limiteSaque;
        throw new ParametroInvalidoException("Senha incorreta");
    }

    public void setLimiteChequeEspecial(float limiteSaque) {
        this.limiteSaque = limiteSaque;
    }

    public float getLimiteTransferencia(int senha) throws ParametroInvalidoException {
        if (this.senha == senha)
            return this.limiteTransferencia;
        throw new ParametroInvalidoException("Senha incorreta");
    }

    public void setLimiteTransferencia(float limiteTransferencia) {
        this.limiteTransferencia = limiteTransferencia;
    }

}