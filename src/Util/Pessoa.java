package Util;

import java.util.Scanner;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private Data dataNascimento;
    private Endereco endereco;
    private String sexo;
    private String estadoCivil;

    public Pessoa(String nome, String cpf, Data dataNascimento, Endereco endereco, String sexo, String estadoCivil) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Data getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(int dia, int mes, int ano) {
        this.dataNascimento = new Data(dia, mes, ano);
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String printPessoa() {
        String Data = this.nome + ";" + this.cpf + ";" + this.dataNascimento.printData() + ";"
                + this.endereco.printEndereco() + ";" + this.sexo + ";" + this.estadoCivil;

        return Data;
    }

    public void cadastraPessoa() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o nome: ");
            this.nome = sc.nextLine();
            System.out.println("Digite o CPF: ");
            this.cpf = sc.nextLine();
            System.out.println("Digite o sexo: ");
            this.sexo = sc.nextLine();
            System.out.println("Digite o estado civil: ");
            this.estadoCivil = sc.nextLine();
            System.out.println("Digite o dia de nascimento: ");
            int dia = sc.nextInt();
            System.out.println("Digite o mês de nascimento: ");
            int mes = sc.nextInt();
            System.out.println("Digite o ano de nascimento: ");
            int ano = sc.nextInt();
            this.dataNascimento = new Data(dia, mes, ano);
            this.endereco.cadastraEndereco();
        }
    }
}
