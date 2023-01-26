package Banco.Agencia.Clientes;

import Util.Data;
import Util.Pessoa;
import Util.Endereco;

public class Cliente extends Pessoa{

    private String escolaridade;

    public Cliente(String nome, String cpf, String sexo, Data dataNascimento, Endereco endereco, String estadoCivil, String escolaridade) {
        super(nome, cpf, sexo, dataNascimento, endereco, estadoCivil);
        this.escolaridade = escolaridade;
    }

    public String getEscolaridade() {
        return this.escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }
    
}
