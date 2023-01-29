package Banco.Agencia.Funcionarios;

import java.util.Scanner;

import Banco.Agencia.Agencia;
import Util.Data;
import Util.Endereco;

public class Gerente extends Funcionario {

    private Data dataIngressoGerente;
    private Agencia agenciaGerenciada;
    private boolean formacaoBasica;
    private boolean estaEmUmaAgencia;
    private static float comissaoGerente = 1000; //Atributo estatico

    public Gerente(String nome, String cpf, String sexo, Data dataNascimento, Endereco endereco, String estadoCivil,
            String nroCarteiraTrabalho, int rg_num,
            String rg_uf, float salario, int anoIngresso, Data dataIngressoGerente, Agencia agenciaGerenciada,
            boolean formacaoBasica, boolean estaEmUmaAgencia) {
        super(nome, cpf, sexo, dataNascimento, endereco, estadoCivil, nroCarteiraTrabalho, rg_num, rg_uf, "Gerente",
                salario, anoIngresso);
        this.dataIngressoGerente = dataIngressoGerente;
        this.agenciaGerenciada = agenciaGerenciada;
        this.formacaoBasica = formacaoBasica;
        // this.formacaoBasica = true;
        if (agenciaGerenciada == null) {
            this.estaEmUmaAgencia = false;
        } else {
            this.estaEmUmaAgencia = true;
        }
    }

    public Gerente() { // Construtor default
        super(null, null, null, null, null, null, null, 0, null, null, 0, 0);
    }

    public Agencia getAgenciaGerenciada() {
        return this.agenciaGerenciada;
    }

    public void setAgencia(Agencia agencia) { // Agencia agencia
        this.agenciaGerenciada = agencia;
        // this.estaEmUmaAgencia = true;
    }

    public Data getdataIngressoGerente() {
        return this.dataIngressoGerente;
    }

    public void setdataIngressoGerente(Data dataIngressoGerente) {
        this.dataIngressoGerente = dataIngressoGerente;
    }

    public boolean getFormacaoBasica() {
        return this.formacaoBasica;
    }

    public void setFormacaoBasica(boolean formacaoBasica) {
        this.formacaoBasica = formacaoBasica;
    }

    public boolean getEstaEmUmaAgencia() {
        return this.estaEmUmaAgencia;
    }

    public void setEstaEmUmaAgencia(boolean estaEmUmaAgencia) {
        this.estaEmUmaAgencia = estaEmUmaAgencia;
    }

    public static float getComissaoGerente() {
        return comissaoGerente;
    }

    public static void setComissaoGerente(float comissaoGerente) {
        Gerente.comissaoGerente = comissaoGerente;
    }

    @Override
    public String printFuncionario() {
        String data = super.printFuncionario() + ";" + this.dataIngressoGerente.printData() + ";" + // this.agenciaGerenciada.print();
               "Formação básica: " + this.formacaoBasica + ";" + "Gerente esta em uma agencia: " + this.estaEmUmaAgencia + ";";

        return data;

    }

    public void cadastraGerente(Agencia agencia){
        try(Scanner sc = new Scanner(System.in)) {
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
    public void calculaSalario(){ // Metodo calcula salario especifico para gerente
        super.calculaSalario();
        this.setSalario(this.getSalario() + Gerente.comissaoGerente);
    }

}
