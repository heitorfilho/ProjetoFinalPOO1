package Banco.Agencia.Funcionarios;

import java.util.Scanner;

import Banco.Agencia.Agencia;
import Util.Data;
import Util.Endereco;

public class Gerente extends Funcionario {
    private boolean formacaoBasica;
    private Data dataIngressoGerente;
    private boolean estaEmUmaAgencia;
    private int numAgenciaGerenciada;
    private Agencia agenciaGerenciada; // tive que colocar como comentario para nao atrapalhar na parte de arquivos
    private static float comissaoGerente = 1000; // Atributo estatico

    public Gerente(String nome, String cpf, Data dataNascimento, Endereco endereco, String sexo, String estadoCivil,
            int numCarteiraTrab, float salario, Data dataIngresso, int rg_num, String rg_uf,
            boolean formacaoBasica,
            Data dataIngressoGerente/* , Agencia agenciaGerenciada, boolean estaEmUmaAgencia */) {
        super(nome, cpf, dataNascimento, endereco, sexo, estadoCivil, numCarteiraTrab, "Gerente", salario, dataIngresso,
                rg_num, rg_uf);
        this.dataIngressoGerente = dataIngressoGerente;
        // this.agenciaGerenciada = agenciaGerenciada;
        this.formacaoBasica = formacaoBasica;
        // this.formacaoBasica = true;
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

    public void setAgencia(int numAgenciaGerenciada) {
        this.numAgenciaGerenciada = numAgenciaGerenciada;
    }

    // public void setAgencia(Agencia agencia) { // Agencia agencia
    // this.agenciaGerenciada = agencia;
    // this.estaEmUmaAgencia = true;
    // }

    public static void setComissaoGerente(float comissaoGerente) {
        Gerente.comissaoGerente = comissaoGerente;
    }

    @Override
    public String printFuncionario() {
        String data = super.printFuncionario() + ";" + this.dataIngressoGerente.printData() + ";" + // this.agenciaGerenciada.print();
                "Formação básica: " + this.formacaoBasica + ";" + "Gerente esta em uma agencia: "
                + this.estaEmUmaAgencia + ";";

        return data;

    }

    public void cadastraGerente(Agencia agencia) {
        try (Scanner sc = new Scanner(System.in)) {
            super.cadastraFuncionarioGerente();
            System.out.println("Digite a data de ingresso do gerente: ");
            System.out.println("Dia: ");
            int dia = sc.nextInt();
            System.out.println("Mês: ");
            int mes = sc.nextInt();
            System.out.println("Ano: ");
            int ano = sc.nextInt();
            this.dataIngressoGerente = new Data(dia, mes, ano);
            this.agenciaGerenciada = agencia;
            System.out.println("O gerente possui formação básica?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int opcao = sc.nextInt();
            if (opcao == 1) {
                this.formacaoBasica = true;
            } else {
                this.formacaoBasica = false;
            }
            this.estaEmUmaAgencia = true;
            System.out.println("Gerente cadastrado com sucesso!");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void calculaSalario() { // Metodo calcula salario especifico para gerente
        super.calculaSalario();
        this.setSalario(this.getSalario() + Gerente.comissaoGerente);
    }

}
