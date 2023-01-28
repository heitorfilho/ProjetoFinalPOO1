package Banco.Agencia.Funcionarios;

import Banco.Agencia.Agencia;
import Util.Data;
import Util.Endereco;

public class Gerente extends Funcionario{

    private Data dataIngresso;
    private Agencia agenciaGerenciada;
    private boolean formacaoBasica;


    public Gerente(String nome, String cpf, String sexo, Data dataNascimento, Endereco endereco, String estadoCivil, String nroCarteiraTrabalho,
        String rgFuncionario, float salario, int anoIngresso, Data dataIngresso, Agencia agenciaGerenciada) {
        super(nome, cpf, sexo, dataNascimento, endereco, estadoCivil, nroCarteiraTrabalho, rgFuncionario, "Gerente", salario, anoIngresso);
        this.dataIngresso = dataIngresso;
        this.agenciaGerenciada = agenciaGerenciada;
        this.formacaoBasica = true;
    }

    public Gerente() { // Construtor default
        super(null, null, null, null, null, null, null, null, null, 0, 0);
    }

    public Agencia getAgenciaGerenciada() {
        return this.agenciaGerenciada; 
    }

    public void setAgencia(Agencia agencia){
        this.agenciaGerenciada = agencia;
    }
    
}
