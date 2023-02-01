package Util;

import java.io.*;
import java.util.*;

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

    public static final String BaseDeDados = "C:\\Users\\Pichau\\Documents\\Faculdade\\3° Periodo\\POO1\\Projeto Final\\ProjetoFinalPOO1\\src\\Dados\\";
    
    //"C:\\Users\\augus\\OneDrive\\Área de Trabalho\\UFU-BSI\\3º Período\\GSI015 - Programacao Orientada a Objetos 1\\3\\ProjetoFinalPOO1\\src\\Dados\\";

    // ------------------------------------------------------------ //
    // -------------------ARQUIVO-PARA-CLIENTES------------------- //
    // ---------------------------------------------------------- //

    //NAO ALTERAR -> ESTA FUNCIONANDO
    public static void salvarArquivoCliente(LinkedList<Cliente> clientes) {

        try {
            FileWriter arq = new FileWriter(BaseDeDados + "Clientes.csv");
            PrintWriter out = new PrintWriter(arq);
            try {
                for (Cliente cliente : clientes) {
                    String linha = cliente.printCliente();
                    out.println(linha);
                }
            } catch (NullPointerException erro) {
                System.out.println("Nao possui nenhum registro" + erro);
            }
            out.close();
            arq.close();
        } catch (IOException erro) {
            System.out.println("Erro na escrita dos dados dos clientes" + erro);
        }

    }

    //NAO ALTERAR -> ESTA FUNCIONANDO
    public static LinkedList<Cliente> carregarClientes() {
        LinkedList<Cliente> clientes = new LinkedList<>();

        try {
            FileReader ent = new FileReader(BaseDeDados + "Clientes.csv");
            BufferedReader br = new BufferedReader(ent);
            String linha;
            String[] campos = null;
            while ((linha = br.readLine()) != null) {
                campos = linha.split(";");
                Data dataNascimento = new Data(Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                        Integer.parseInt(campos[4]));
                Endereco endCliente = new Endereco(campos[5], Integer.parseInt(campos[6]), campos[7], campos[8],
                        campos[9], campos[10], campos[11], Integer.parseInt(campos[12]));
                clientes.add(new Cliente(campos[0], campos[1], dataNascimento, endCliente, campos[13], campos[14],
                        campos[15]));
            }
            br.close();
            ent.close();
        } catch (IOException erro) {
            System.out.println("Erro na leitura dos dados dos clientes" + erro);
        }
        return clientes;
    }

    // ------------------------------------------------------------ //
    // -------------------ARQUIVO-PARA-CONTAS--------------------- //
    // ---------------------------------------------------------- //

    // MAIOR PARTE FUNCIONANDO, SO NAO ENTENDI A LOGICA DO CPF ENCONTRADO   
    public static LinkedList<Conta> carregarContas(int numeroAgencia, LinkedList<Cliente> clientes) {
        String numAgencia = String.valueOf(numeroAgencia);
        LinkedList<Conta> contas = new LinkedList<>();

        try {
            FileReader ent = new FileReader(BaseDeDados + "\\Contas\\" + numAgencia + "Contas.csv");
            try (BufferedReader br = new BufferedReader(ent)) {
                String linha;
                String[] campos = null;
                while ((linha = br.readLine()) != null) {
                    campos = linha.split(";");
                    Data AberturaConta = new Data(Integer.parseInt(campos[8]), Integer.parseInt(campos[9]),
                            Integer.parseInt(campos[10]));
                    Data UltimaMovimentacao = new Data(Integer.parseInt(campos[11]), Integer.parseInt(campos[12]),
                            Integer.parseInt(campos[13]));
                    Cliente primario = new Cliente();
                    Cliente secundario = new Cliente();
                    boolean CPF_Encontrado = false;

                    for (Cliente cliente : clientes) {
                        Cliente percorre = cliente;
                        if (percorre.getCpf().equals(campos[5])) {
                            primario = percorre;
                            CPF_Encontrado = true;
                        } else if (percorre.getCpf().equals(campos[6])) {
                            secundario = percorre;
                        }

                    }

                    //if (!CPF_Encontrado)
                        //throw new IllegalArgumentException("Cliente nao encontrado");

                    Conta nova;
                    switch (campos[0]) {
                        case "Corrente":
                            nova = new Corrente(Integer.parseInt(campos[1]), Integer.parseInt(campos[2]),
                                    Float.parseFloat(campos[3]), Boolean.parseBoolean(campos[4]), primario,
                                    Integer.parseInt(campos[7]), AberturaConta, Float.parseFloat(campos[13]),
                                    Float.parseFloat(campos[14]));
                            if (Boolean.parseBoolean(campos[4])) {
                                nova.setClienteSecundario(secundario);
                            }
                            nova.setUltimaMovimentacao(UltimaMovimentacao);
                            contas.add(nova);
                            break;
                        case "Poupanca":
                            nova = new Poupanca(Integer.parseInt(campos[1]), Integer.parseInt(campos[2]),
                                    Float.parseFloat(campos[3]), Boolean.parseBoolean(campos[4]), primario,
                                    Integer.parseInt(campos[7]), AberturaConta, Float.parseFloat(campos[13]));
                            if (Boolean.parseBoolean(campos[4])) {
                                nova.setClienteSecundario(secundario);
                            }
                            nova.setUltimaMovimentacao(UltimaMovimentacao);
                            contas.add(nova);
                            break;

                        case "Salario":
                            nova = new Salario(Integer.parseInt(campos[1]), Integer.parseInt(campos[2]),
                                    Float.parseFloat(campos[3]), Boolean.parseBoolean(campos[4]), primario,
                                    Integer.parseInt(campos[7]), AberturaConta, Float.parseFloat(campos[13]),
                                    Float.parseFloat(campos[14]));
                            if (Boolean.parseBoolean(campos[4])) {
                                nova.setClienteSecundario(secundario);
                            }
                            nova.setUltimaMovimentacao(UltimaMovimentacao);
                            contas.add(nova);
                            break;
                        default:
                            break;
                     
                    }
                }
                br.close();
            } catch (NumberFormatException e) { // funcao para tratar o erro de conversao de string para int
                e.printStackTrace(); // imprime o erro
            }
        } catch (IOException erro) {
            System.out.println(" Arquivo nao encontrado ou corrompido:" + numAgencia + "Contas.csv");
        }
        return contas;
    }

    // NAO ALTERAR -> ESTA FUNCIONANDO  
    public static void salvarArquivoConta(int numeroAgencia, LinkedList<Conta> contas) {
        String numAgencia = String.valueOf(numeroAgencia);
        try {
            FileWriter arq = new FileWriter(BaseDeDados + "\\Contas\\" + numAgencia + "Contas.csv");
            PrintWriter out = new PrintWriter(arq);
            try {
                for (Conta conta : contas) {
                    String linha = conta.printConta();
                    out.println(linha);
                }
            } catch (NullPointerException erro) {
                System.out.println("Nao possui nenhum registro" + erro);
            }
            out.close();
            arq.close();
        } catch (IOException erro) {
            System.out.println("Erro na escrita dos dados das contas" + erro);
        }

    }

    // ------------------------------------------------------------ //
    // -------------------ARQUIVO-PARA-AGENCIAS------------------- //
    // ---------------------------------------------------------- //

    // NAO ALTERAR -> ESTA FUNCIONANDO
    public static LinkedList<Agencia> carregarAgencias() {
        LinkedList<Agencia> agencias = new LinkedList<>();

        try {
            FileReader ent = new FileReader(BaseDeDados + "Agencias.csv");
            BufferedReader br = new BufferedReader(ent);
            String linha;
            String[] campos = null;
            while ((linha = br.readLine()) != null) {
                campos = linha.split(";");
                Agencia nova = new Agencia(campos[0], Integer.parseInt(campos[1]));
                Endereco endAgencia = new Endereco(campos[2], Integer.parseInt(campos[3]), campos[4], campos[5],
                        campos[6], campos[7], campos[8], Integer.parseInt(campos[9]));
                nova.setEnderecoAgencia(endAgencia);
                agencias.add(nova);
            }
            br.close();
            ent.close();
        } catch (IOException erro) {
            System.out.println("Arquivo nao encontrado ou corrompido: Agencias.csv" + erro);
        }
        return agencias;
    }

    // NAO ALTERAR -> ESTA FUNCIONANDO
    public static void salvarArquivoAgencia(LinkedList<Agencia> agencias) {

        try {
            FileWriter arq = new FileWriter(BaseDeDados + "Agencias.csv");
            PrintWriter out = new PrintWriter(arq);
            try {
                for (Agencia agencia : agencias) {
                    String linha = agencia.printAgencia();
                    out.println(linha);
                }
            } catch (NullPointerException erro) {
                System.out.println("Nao possui nenhum registro" + erro);
            }
            out.close();
            arq.close();
        } catch (IOException erro) {
            System.out.println("Erro na escrita dos dados das agencias" + erro);
        }

    }

    // ------------------------------------------------------------ //
    // -------------------ARQUIVO-PARA-FUNCIONARIOS--------------- //
    // ---------------------------------------------------------- //

    public static LinkedList<Pessoa> carregarFuncionarios(int numAgencia) {
        LinkedList<Pessoa> funcionarios = new LinkedList<>();
        String numeroAgencia = String.valueOf(numAgencia);

        try {
            FileReader ent = new FileReader(BaseDeDados + "\\Funcionarios\\Funcionarios.csv"); //" + numeroAgencia + 
            BufferedReader br = new BufferedReader(ent);
            String linha;
            String[] campos = null;
            while ((linha = br.readLine()) != null) {
                campos = linha.split(";");
                Data[] Datas = new Data[3];
                Datas[0] = new Data(Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                        Integer.parseInt(campos[4]));
                Datas[1] = new Data(Integer.parseInt(campos[18]), Integer.parseInt(campos[19]),
                        Integer.parseInt(campos[20]));
                Endereco End = new Endereco(campos[5], Integer.parseInt(campos[6]), campos[7], campos[8], campos[9],
                        campos[10], campos[11], Integer.parseInt(campos[12]));
                if (campos[16].equals("Gerente")) {
                    Datas[2] = new Data(Integer.parseInt(campos[26]), Integer.parseInt(campos[27]),
                            Integer.parseInt(campos[28]));

                    Funcionario novo = new Gerente(campos[0], campos[1], Datas[0], End, campos[13], campos[14],
                            Integer.parseInt(campos[15]), Float.parseFloat(campos[17]), Datas[1],
                            Integer.parseInt(campos[21]), campos[22], Boolean.parseBoolean(campos[23]), Datas[2]);
                    funcionarios.add(novo);
                } else {
                    Funcionario novo = new Funcionario(campos[0], campos[1], Datas[0], End, campos[13], campos[14],
                            Integer.parseInt(campos[15]), campos[16], Float.parseFloat(campos[17]), Datas[1],
                            Integer.parseInt(campos[21]), campos[22]);
                    funcionarios.add(novo);
                }
            }
            br.close();
            ent.close();
        } catch (IOException erro) {
            System.out.println("Arquivo nao encontrado ou corrompido: Funcionarios.csv" + erro);
        }
        return funcionarios;
    }

    public static void salvarArquivoFuncionarios(int numeroAgencia, LinkedList<Pessoa> funcionarios) {
        String numAgencia = String.valueOf(numeroAgencia);
        try {
            FileWriter arq = new FileWriter(BaseDeDados + "\\Funcionarios\\" + numAgencia + "Funcionarios.csv");
            PrintWriter out = new PrintWriter(arq);
            try {
                for (int i = 0; i < funcionarios.size(); i++) {
                    Funcionario percorre = (Funcionario) funcionarios.get(i);
                    String linha;

                    if (percorre.getCargo().equals("Gerente"))
                        linha = percorre.printGerente();
                    else
                        linha = percorre.printFuncionario();
                    out.println(linha);
                }
            } catch (NullPointerException e) {
            }
            out.close();
            arq.close();
        } catch (IOException erro) {
            System.out.println("Erro na escritas dos dados dos funcionarios" + erro);
        }
    }

    // ------------------------------------------------------------ //
    // -------------------ARQUIVO-PARA-MOVIMENTACOES-------------- //
    // ---------------------------------------------------------- //

    public static LinkedList<Movimentacao> carregarMovimentacoes(int numeroConta, int numeroAgencia) {
        String numAgencia = String.valueOf(numeroAgencia);
        String numConta = String.valueOf(numeroConta);
        LinkedList<Movimentacao> movimentacoes = new LinkedList<>();

        try {

            FileReader ent = new FileReader(
                    BaseDeDados + "\\Contas\\Movimentacoes\\" + numAgencia + numConta + "Movimentacoes.csv");
            BufferedReader br = new BufferedReader(ent);
            String linha;
            String[] campos = null;
            while ((linha = br.readLine()) != null) {
                campos = linha.split(";");
                Data nova = new Data(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]),
                        Integer.parseInt(campos[2]));
                Movimentacao mov = new Movimentacao(nova, Float.parseFloat(campos[3]), campos[4],
                        (campos[5]), Integer.parseInt(campos[6]), Integer.parseInt(campos[7]),
                        Integer.parseInt(campos[8]));
                movimentacoes.add(mov);
            }
            br.close();
        } catch (IOException erro) {
            System.out.println("Arquivo nao encontrado ou corrompido:" + numAgencia + numConta + "Movimentacoes.csv");
        }
        return movimentacoes;
    }

    public static void salvarArquivoMovimentacoes(int numeroConta, int numeroAgencia,
            LinkedList<Movimentacao> movimentacoes) {
        String numAgencia = String.valueOf(numeroConta);
        String numConta = String.valueOf(numeroAgencia);

        try {
            FileWriter arq = new FileWriter(
                    BaseDeDados + "\\Contas\\Movimentacoes\\" + numAgencia + numConta + "Movimentacoes.csv");
            PrintWriter out = new PrintWriter(arq);
            try {
                for (int i = 0; i < movimentacoes.size(); i++) {
                    String linha = movimentacoes.get(i).printMovimentacoes();
                    out.println(linha);
                }
            } catch (NullPointerException e) {
            }
            out.close();
            arq.close();
        } catch (IOException erro) {
            System.out.println("Erro na escrita dos dados" + numAgencia + numConta + "Movimentacoes.csv");
        }
    }
}
