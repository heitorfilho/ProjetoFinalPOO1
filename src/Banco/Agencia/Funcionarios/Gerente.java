package Banco.Agencia.Funcionarios;

// import java.util.Scanner;

import Banco.Agencia.Agencia;
import Util.Data;
import Util.Endereco;

public class Gerente extends Funcionario {
    private boolean formacaoBasica;
    private Data dataIngressoGerente;
    private boolean estaEmUmaAgencia;
    private int numAgencia;
    private Agencia agenciaGerenciada; // tive que colocar como comentario para nao atrapalhar na parte de arquivos
    private static float comissaoGerente = 1000; // Atributo estatico

    public Gerente(String nome, String cpf, Data dataNascimento, Endereco endereco, String sexo, String estadoCivil,
            int numCarteiraTrab, float salario, Data dataIngresso, int rg_num, String rg_uf,
            boolean formacaoBasica,
            Data dataIngressoGerente) {
        super(nome, cpf, dataNascimento, endereco, sexo, estadoCivil, numCarteiraTrab, "Gerente", salario, dataIngresso,
                rg_num, rg_uf);
        this.dataIngressoGerente = dataIngressoGerente;
        this.formacaoBasica = formacaoBasica;
        if (agenciaGerenciada == null) {
            this.estaEmUmaAgencia = false;
        } else {
            this.estaEmUmaAgencia = true;
        }
    }

    public Gerente() { // Construtor default
        super(null, null, null, null, null, null, 0, null, 0, null, 0, null);
    }

    public Data getdataIngressoGerente() {
        return this.dataIngressoGerente;
    }

    public boolean getFormacaoBasica() {
        return this.formacaoBasica;
    }

    public boolean getEstaEmUmaAgencia() {
        return this.estaEmUmaAgencia;
    }

    public static float getComissaoGerente() {
        return comissaoGerente;
    }

    public int getNumAgencia() {
        return this.numAgencia;
    }

    public Agencia getAgenciaGerenciada() {
        return this.agenciaGerenciada;
    }

    public void setdataIngressoGerente(Data dataIngressoGerente) {
        this.dataIngressoGerente = dataIngressoGerente;
    }

    public void setFormacaoBasica(boolean formacaoBasica) {
        this.formacaoBasica = formacaoBasica;
    }

    public void setEstaEmUmaAgencia(boolean estaEmUmaAgencia) {
        this.estaEmUmaAgencia = estaEmUmaAgencia;
    }

    public void setNumAgencia(int numAgencia) {
        this.numAgencia = numAgencia;
    }

    public static void setComissaoGerente(float comissaoGerente) {
        Gerente.comissaoGerente = comissaoGerente;
    }

    @Override
    public void calculaSalario() { // Metodo calcula salario especifico para gerente

        if (this.getSalarioAjustado() != true) {
            super.calculaSalario();
            this.setSalario(this.getSalario() + Gerente.comissaoGerente);
        } else {
            System.out.println("Gerente ja teve seu salario ajustado");
        }
    }

    @Override
    public String printFuncionario() {
        String data = super.printFuncionario() + this.dataIngressoGerente.printData() + ";" + // this.agenciaGerenciada.print();
                this.formacaoBasica + ";" + this.estaEmUmaAgencia + ";";

        return data;

    }

}
