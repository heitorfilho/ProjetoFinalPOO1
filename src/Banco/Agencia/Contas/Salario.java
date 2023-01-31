package Banco.Agencia.Contas;

import java.util.Scanner;

import Banco.Agencia.Agencia;
import Banco.Agencia.Clientes.Cliente;
import Util.Data;

public class Salario extends Conta {

    private float limiteSaque;
    private float limiteTransferencia;

    public Salario(int numConta, int senha, float saldo, boolean conjunta,
            Cliente clientePrimario, int numAgencia,
            Data aberturaConta, float limiteSaque, float limiteTransferencia) {
        super(numConta, senha, saldo, conjunta, clientePrimario, numAgencia, aberturaConta);
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
        super.printConta();
        String data = this.limiteSaque + ";" + this.limiteTransferencia + ";";

        return data;
    }

    // CRIA CONTA//
    @Override
    public void criaConta(Agencia agencia) {
        try (Scanner sc = new Scanner(System.in);) {
            super.criaConta(agencia);
            System.out.println("Digite o limite de saque:");
            float novoLimiteSaque = sc.nextFloat();
            if (novoLimiteSaque > this.getSaldo()) { // limite de saque nao pode ser maior que o saldo no momento de
                                                     // criacao da conta
                this.limiteSaque = this.getSaldo();
                System.out.println("Limite se saque nao pode ser maior que o saldo da conta.");
            } else {
                this.limiteSaque = novoLimiteSaque;
            }
            System.out.println("Digite o limite de transferencia:");
            float novoLimiteTransferencia = sc.nextFloat();
            if (novoLimiteSaque > this.getSaldo()) { // limite de transferencia nao pode ser maior que o saldo no
                                                     // momento de criacao da conta
                this.limiteTransferencia = this.getSaldo();
                System.out.println("Limite se transferencia nao pode ser maior que o saldo da conta.");
            } else {
                this.limiteTransferencia = novoLimiteTransferencia;
            }
            System.out.println("Conta criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}