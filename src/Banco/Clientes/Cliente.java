package Banco.Clientes;

import java.io.Serializable; // para serializar os objetos e salvar em arquivo
import Util.*;

public class Cliente extends Pessoa implements Serializable {
    private String escolaridade;

    public Cliente(String nome, String cpf, Data dataNascimento, Endereco endereco, String sexo, String estadoCivil,
            String escolaridade) {
        super(nome, cpf, dataNascimento, endereco, sexo, estadoCivil);
        this.escolaridade = escolaridade;
    }

    public Cliente() { // Construtor default
        super(null, null, null, null, null, null);
    }

    public Cliente(String nome, String cpf) { // Construtor com nome e cpf
        super(nome, cpf, null, null, null, null);
    }

    /////////////////////////////
    ///// GETTERS E SETTERS /////
    /////////////////////////////

    public String getEscolaridade() { 
        return this.escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String printCliente() {
        String data = super.printPessoa() + ";" + this.escolaridade;
        return data;
    }

}
