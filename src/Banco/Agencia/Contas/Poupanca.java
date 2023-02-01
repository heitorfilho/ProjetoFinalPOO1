package Banco.Agencia.Contas;

import Util.Data;
import Banco.Agencia.Clientes.Cliente;
import Banco.Agencia.Contas.Movimentacoes.*;

public class Poupanca extends Conta {

    private float rendimentoMesAtual;

    public Poupanca(int numConta, int senha, float saldo, boolean conjunta,
            Cliente clientePrimario, int numAgencia,
            Data aberturaConta, float rendimentoMesAtual) {
        super(numConta, senha, saldo, conjunta, clientePrimario, numAgencia, aberturaConta);
        this.tipoConta = "Poupanca";
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
        Movimentacao nova = new Movimentacao();
        nova.rendimento(valor);
        movimentacoes.add(nova);
    }

    public String printConta() {
        String data = super.printConta() + this.rendimentoMesAtual + ";";

        return data;
    }


}