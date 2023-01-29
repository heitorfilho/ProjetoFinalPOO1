package Banco.Agencia.Contas;

import Util.Data;

import java.util.Scanner;

import Banco.Agencia.Agencia;
import Banco.Agencia.Clientes.Cliente;

public class Corrente extends Conta {

    private float limCheque;
    private float taxaAdmin;

    public Corrente(int numConta, int senha, float saldo, boolean conjunta,
            Cliente clientePrimario, int numAgencia,
            Data aberturaConta, float limite, float taxAdmin) { // Precisa fazer alteracao para Data
        super(numConta, senha, saldo, conjunta, clientePrimario, numAgencia, aberturaConta);
        this.limCheque = 0;
        this.taxaAdmin = 30f;
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
        super.printConta();
        String data = this.limCheque + ";" + this.taxaAdmin + ";";

        return data;
    }

    // CRIA CONTA//

    @Override
    public void criaConta(Agencia agenciaConta) {
        try (Scanner sc = new Scanner(System.in)) {
            super.criaConta(agenciaConta);
            System.out.println("Digite o limite de cheque especial:");
            float novoLimite = sc.nextFloat();
            if (novoLimite > this.getSaldo()) { // limite de cheque especial nao pode ser maior que o saldo no momento
                                                // de criacao da conta
                this.limCheque = this.getSaldo();
            } else {
                this.limCheque = novoLimite;
            }
            System.out.println("A taxa de administração e de 30 reais.");
            this.taxaAdmin = 30f;
            System.out.println("Conta criada com sucesso!");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}