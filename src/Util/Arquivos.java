package Util;

import java.io.*;
import java.util.*;

import javax.annotation.processing.Filer;

// import Util.Exceptions.*;

import Banco.Agencia.Agencia;
import Banco.Agencia.Contas.Conta;
import Banco.Agencia.Contas.Corrente;
import Banco.Agencia.Contas.Poupanca;
import Banco.Agencia.Contas.Salario;
import Banco.Agencia.Contas.Movimentacoes.*;

import Banco.Agencia.Clientes.Cliente;
import Banco.Agencia.Funcionarios.Funcionario;
import Banco.Agencia.Funcionarios.Gerente;

public class Arquivos {

    public static final String BaseDeDados = "C:\\Users\\augus\\OneDrive\\Área de Trabalho\\UFU-BSI\\3º Período\\GSI015 - Programação Orientada a Objetos 1\\3\\ProjetoFinalPOO1\\src\\Dados\\";

    //////////////////////////////////
    ///// ARQUIVO PARA CLIENTES /////
    ////////////////////////////////

    public static void salvarArquivoCliente(LinkedList<Cliente> clientes) {
        
        try {
            FileWriter arq = new FileWriter(BaseDeDados + "Clientes.csv");
            PrintWriter out = new PrintWriter(arq);
            try {
                for(Cliente cliente : clientes){
                    String linha = cliente.printCliente();
                    out.println(linha);
                } 
            } catch(NullPointerException erro) {
                System.out.println("Não possui nenhum registro" + erro);
            } 
            out.close(); 
            arq.close();
        } catch(IOException erro) {
            System.out.println("Erro na escrita dos dados dos clientes" + erro);
        }
        
    }

    public static LinkedList<Cliente> carregarClientes(){
        LinkedList<Cliente> clientes = new LinkedList<>();

        try {
            FileReader ent = new FileReader(BaseDeDados + "Clientes.csv");
            BufferedReader br = new BufferedReader(ent);
            String linha;
            String []campos = null;
            while((linha = br.readLine()) != null){
                campos = linha.split(";");
                Data dataNascimento = new Data(Integer.parseInt(campos[2]), Integer.parseInt(campos[3]), Integer.parseInt(campos[4]));
                Endereco endCliente = new Endereco(campos[5], campos[6], campos[7], campos[8], Integer.parseInt(campos[9]), linha, Integer.parseInt(campos[10]), campos[11]);
                clientes.add(new Cliente(campos[0], campos[1], dataNascimento, endCliente, campos[13], campos[14], campos[15]));
            } 
            br.close();
            ent.close();
        } catch(IOException erro){
            System.out.println("Erro na leitura dos dados dos clientes" + erro);
        }
        return clientes;
    }

    //////////////////////////////////
    //// ARQUIVO PARA FUNCIONARIOS //
    ////////////////////////////////
    
    public static LinkedList<Funcionario> carregarFuncionarios(int numAgencia){
        LinkedList<Pessoa> funcionarios = new LinkedList<>();
        String numeroAgencia = String.valueOf(numAgencia);

        try {
            FileReader ent = new FileReader(BaseDeDados + "\\Funcionarios\\" + numeroAgencia + "Funcionarios.csv");
            BufferedReader br = new BufferedReader(ent);
            String linha;
            String []campos = null;
            while((linha = br.readLine()) != null){
                campos = linha.split(";");
                Data[] Datas = new Data[3];
                Datas[0] = new Data(Integer.parseInt(campos[2]), Integer.parseInt(campos[3]), Integer.parseInt(campos[4]));
                Datas[1] = new Data(Integer.parseInt(campos[2]), Integer.parseInt(campos[3]), Integer.parseInt(campos[4]));
                
                Endereco endFunc = new Endereco(campos[5], campos[6], campos[7], campos[8], Integer.parseInt(campos[9]), linha, Integer.parseInt(campos[10]), campos[11]);
                if(campos[16].equals("Gerente")){
                    Datas[2]= new Data
                }
                funcionarios.add(new Funcionario(campos[0], campos[1], dataNascimento, endCliente, campos[13], campos[14], campos[15], Integer.parseInt(campos[16]), campos[17], campos[18], Float.parseFloat(campos[19], Integer.parseInt(campos[20]) ));
            } 
            br.close();
            ent.close();
        } catch(IOException erro){
            System.out.println("Erro na leitura dos dados dos clientes" + erro);
        }
        return funcionarios;
    }

    public static void salvarArquivoFuncionario(LinkedList<Funcionario> funcionarios) {
        
        try {
            FileWriter arq = new FileWriter(BaseDeDados + "Funcionarios.csv");
            PrintWriter out = new PrintWriter(arq);
            try {
                for(Funcionario funcionario : funcionarios){
                    String linha = funcionario.printFuncionario();
                    out.println(linha);
                } 
            } catch(NullPointerException erro) {
                System.out.println("Não possui nenhum registro" + erro);
            } 
            out.close(); 
            arq.close();
        } catch(IOException erro) {
            System.out.println("Erro na escrita dos dados dos funcionarios" + erro);
        }
        
    }

    
}
