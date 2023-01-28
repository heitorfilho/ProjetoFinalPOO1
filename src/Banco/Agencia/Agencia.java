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
    //private LinkedList<Pessoa> pessoas;
    private LinkedList<Cliente> clientes;


    public Agencia(String nomeAgencia, int numeroAgencia, Endereco enderecoAgencia){
        this.nomeAgencia = nomeAgencia;
        this.numeroAgencia = numeroAgencia;
        this.enderecoAgencia = enderecoAgencia;
        this.contas = new LinkedList<Conta>();
        this.funcionarios = new LinkedList<Funcionario>();
        this.clientes = new LinkedList<Cliente>();
    }
    public Agencia(String nomeAgencia, int numeroAgencia, Endereco enderecoAgencia, Gerente gerenteAgencia){
        this.nomeAgencia = nomeAgencia;
        this.numeroAgencia = numeroAgencia;
        this.enderecoAgencia = enderecoAgencia;
        this.gerenteAgencia = gerenteAgencia;
        this.contas = new LinkedList<Conta>();
        this.funcionarios = new LinkedList<Funcionario>();
        this.clientes = new LinkedList<Cliente>();
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
    
}
