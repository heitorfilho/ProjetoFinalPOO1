package Banco.Agencia;

import java.util.*;


import Banco.Agencia.Contas.Conta;
import Banco.Agencia.Clientes.Cliente;
import Banco.Agencia.Funcionarios.Funcionario;
import Banco.Agencia.Funcionarios.Gerente;
import Util.*;

public class Agencia {

    private int numeroAgencia;
    private String nomeAgencia;
    private Endereco enderecoAgencia;
    private Gerente gerenteAgencia;
    private LinkedList<Conta> contas;
    private LinkedList<Funcionario> funcionarios;
    // private LinkedList<Pessoa> pessoas;
    // private LinkedList<Cliente> clientes;


    public Agencia(String nomeAgencia, int numeroAgencia, Endereco enderecoAgencia){
        this.nomeAgencia = nomeAgencia;
        this.numeroAgencia = numeroAgencia;
        this.enderecoAgencia = enderecoAgencia;
        this.contas = new LinkedList<Conta>();
        this.funcionarios = new LinkedList<Funcionario>();
        // this.clientes = new LinkedList<Cliente>();
    }
    public Agencia(String nomeAgencia, int numeroAgencia, Endereco enderecoAgencia, Gerente gerenteAgencia){
        this.nomeAgencia = nomeAgencia;
        this.numeroAgencia = numeroAgencia;
        this.enderecoAgencia = enderecoAgencia;
        this.gerenteAgencia = gerenteAgencia;
        this.contas = new LinkedList<Conta>();
        this.funcionarios = new LinkedList<Funcionario>();
        // this.clientes = new LinkedList<Cliente>();
    }

//GETTERS E SETTERS//

    public int getNumeroAgencia(){
        return this.numeroAgencia;
    }

    public String getNomeAgencia(){
        return this.nomeAgencia;
    }

    public Endereco getEnderecoAgencia(){
        return this.enderecoAgencia;
    }

    public Gerente getGerenteAgencia(){
        return this.gerenteAgencia;
    }

    public void setGerenteAgencia(Gerente gerente){
        this.gerenteAgencia = gerente;
        gerente.setAgencia(this);
    }
    
    public LinkedList<Conta> getContas(){
        return this.contas;
    }
    
    public LinkedList<Funcionario> getFuncionarios(){
        return this.funcionarios;
    }
    
    public void setContas(LinkedList<Conta> contas){
        this.contas = contas;
    }

    public void setFuncionarios(LinkedList<Funcionario> funcionarios){
        this.funcionarios = funcionarios;
    }

    public void setNomeAgencia(String nomeAgencia){
        this.nomeAgencia = nomeAgencia;
    }
    
    public void setNumeroAgencia(int numeroAgencia){
        this.numeroAgencia = numeroAgencia;
    }

    public void setEnderecoAgencia(Endereco enderecoAgencia){
        this.enderecoAgencia = enderecoAgencia;
    }

    public void criaContaCorrente(Conta contaNova){
        this.contas.add(contaNova);
        //contaNova.criaConta(this);
    }

    public void criaContaPoupanca(Conta contaNova){
        this.contas.add(contaNova);
        //contaNova.criaConta(this);
    }

    public void criaContaSalario(Conta contaNova){
        this.contas.add(contaNova);
        //contaNova.criaConta(this);
    }

///////////////////////////////////
    /*
    Banco.criaConta(); //Banco.java
    qual agencia? = 1 //Banco.java
    conta vai ser oq? = corrente //Banco.java
    conta cont1; //Banco.java
    cont1 = new ContaCorrente(); //Banco.java
    agencia1.criaContaCorrente(cont1); //Banco.java - Agencia.java - ContaCorrente.java

    Banco CancoDoHeitor = new Banco();
    */
///////////////////////////////////
    
}
