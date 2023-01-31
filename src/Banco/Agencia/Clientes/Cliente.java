package Banco.Agencia.Clientes;

import java.util.Scanner;

import Banco.Agencia.Agencia;

/*import Util.Data;
import Util.Pessoa;
import Util.Endereco;*/

import Util.*;

public class Cliente extends Pessoa {
    private String escolaridade;
    private Agencia agenciaCadastrada;

    public Cliente(String nome, String cpf, Data dataNascimento, Endereco endereco, String sexo, String estadoCivil,
            String escolaridade/*, Agencia agenciaCadastrada*/) {
        super(nome, cpf,dataNascimento, endereco, sexo, estadoCivil);
        this.escolaridade = escolaridade;
        //this.agenciaCadastrada = agenciaCadastrada;
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

    public Agencia getAgenciaCadastrada() {
        return this.agenciaCadastrada;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public void setAgenciaCadastrada(Agencia agenciaCadastrada) {
        this.agenciaCadastrada = agenciaCadastrada;
    }

    public String printCliente() {
        String data = super.printPessoa() + ";" + this.escolaridade;
        return data;
    }

    public void cadastraCliente(Agencia agencia) {
        try(Scanner sc = new Scanner(System.in)) {
            super.cadastraPessoa();
            System.out.println("Digite a escolaridade: ");
            this.escolaridade = sc.nextLine();
            this.agenciaCadastrada = agencia;
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
