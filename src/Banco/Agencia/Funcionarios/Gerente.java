package Banco.Agencia.Funcionarios;

import Banco.Agencia.Agencia;
import Util.Data;
import Util.Endereco;

public class Gerente extends Funcionario{

    private Agencia agenciaGerenciada;


    public Gerente(String nome, String cpf, String sexo, Data dataNascimento, Endereco endereco, String estadoCivil,
            String escolaridade, Agencia agenciaGerenciada) {
        //super(nome, cpf, sexo, dataNascimento, endereco, estadoCivil, escolaridade);
        this.agenciaGerenciada = agenciaGerenciada;
    }

    public Gerente() { // Construtor default
        //super(null, null, null, null, null, null, null);
    }

    public void setAgecencia(Agencia agencia){
        this.agenciaGerenciada = agencia;
    }
    
}
