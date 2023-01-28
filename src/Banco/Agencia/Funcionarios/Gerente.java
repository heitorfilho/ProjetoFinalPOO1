package Banco.Agencia.Funcionarios;

import Banco.Agencia.Agencia;
import Util.Data;
import Util.Endereco;

public class Gerente extends Funcionario {

    private Data dataIngressoGerente;
    private Agencia agenciaGerenciada;
    private boolean formacaoBasica;
    private boolean estaEmUmaAgencia;

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

    public void setAgencia(Agencia agencia) {
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

    @Override
    public String printFuncionario() {
        String data = super.printFuncionario() + ";" + this.dataIngressoGerente.printData() + ";" + // this.agenciaGerenciada.print();
               "Formacao basica: " + this.formacaoBasica + ";" + "Gerente esta em uma agencia: " + this.estaEmUmaAgencia + ";";

        return data;

    }

}
