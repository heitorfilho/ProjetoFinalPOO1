package Banco.Agencia.Contas;

import Util.Data;
import Banco.Agencia.Agencia;
import Banco.Agencia.Clientes.Cliente;

public class Poupanca extends Conta {

    private float rendimentoMesAtual;

    public Poupanca(int numConta, int senha, float saldo, boolean conjunta,
            Cliente clientePrimario, int numAgencia,
            Data aberturaConta, float rendimentoMesAtual) {
        super(numConta, senha, saldo, conjunta, clientePrimario, numAgencia, aberturaConta);
        this.rendimentoMesAtual = 0.5f;
    }

    @Override
    public String saidaArquivo() {
        return "Poupanca;"+ super.saidaArquivo() + ";" + this.rendimentoMesAtual ;
    }
    
    public float getRendimentoMesAtual() {
        return this.rendimentoMesAtual;
    }

    public void setRendimentoMesAtual(float rendimentoMesAtual) {
        this.rendimentoMesAtual = rendimentoMesAtual;
    }

    public void aplicarRendimento() {
        Float valor = this.saldo * this.rendimentoMesAtual;
        this.saldo += valor;
    }

    public String printConta() {
        super.printConta();
        String data = this.rendimentoMesAtual + ";";

        return data;
    }

    // CRIA CONTA//

    @Override
    public void criaConta(Agencia agencia) {
        super.criaConta(agencia);
        this.rendimentoMesAtual = 0.5f;
        System.out.println("O rendimento mensal atual eh de 0.5%");
        System.out.println("Conta criada com sucesso!");
    }

}