package Banco.Agencia.Clientes;

/*import Util.Data;
import Util.Pessoa;
import Util.Endereco;*/

import Util.*;

public class Cliente extends Pessoa {
    private String escolaridade;
    // private Agencia agenciaCadastrada;

    public Cliente(String nome, String cpf, String sexo, Data dataNascimento, Endereco endereco, String estadoCivil,
            String escolaridade) {
        super(nome, cpf, sexo, dataNascimento, endereco, estadoCivil);
        this.escolaridade = escolaridade;
    }

    public Cliente() { // Construtor default
        super(null, null, null, null, null, null);
    }

    public Cliente(String nome, String cpf) { // Construtor com nome e cpf
        super(nome, cpf, null, null, null, null);
    }

    public String printCliente() {
        String data = super.printPessoa() + ";" + this.escolaridade;
        return data;
    }

    public String getEscolaridade() {
        return this.escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }
}
