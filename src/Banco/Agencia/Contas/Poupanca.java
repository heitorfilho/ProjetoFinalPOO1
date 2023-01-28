package Banco.Agencia.Contas;

import Util.Data;
import Banco.Agencia.Agencia;
import Banco.Agencia.Clientes.Cliente;

public class Poupanca extends Conta {

    private float rendimentoMesAtual;

    public Poupanca(int nroConta, int senha, float saldo, boolean conjunta,
            Cliente cliente_primario, Agencia agencia,
            Data aberturaConta) {
        super(nroConta, senha, saldo, conjunta, cliente_primario, agencia, aberturaConta);
        this.rendimentoMesAtual = 0.5f;
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

    //CRIA CONTA//

    @Override
    public void criaConta(Agencia agencia){
        super.criaConta(agencia);
        this.rendimentoMesAtual = 0.5f;
        System.out.println("O rendimento mensal atual eh de 0.5%");
        System.out.println("Conta criada com sucesso!");
    }

}