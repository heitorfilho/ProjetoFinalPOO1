package Banco.Agencia.Funcionarios;


import Util.Data;
import Util.Endereco;
import Util.Pessoa;

public class Funcionario extends Pessoa{

    private String nroCarteiraTrabalho;
    private String rgFuncionario;
    private String cargo;
    private float salario;
    private int anoIngresso;


    public Funcionario(String nome, String cpf, String sexo, Data dataNascimento, Endereco endereco, String estadoCivil,
        String nroCarteiraTrabalho, String rgFuncionario, String cargo, float salario, int anoIngresso) {
        super(nome, cpf, sexo, dataNascimento, endereco, estadoCivil);
        this.nroCarteiraTrabalho = nroCarteiraTrabalho;
        this.rgFuncionario = rgFuncionario;
        this.cargo = cargo;
        this.salario = salario;
        this.anoIngresso = anoIngresso;
    }

    public Funcionario() {
        super(null, null, null, null, null, null);
    }

    
    
}
