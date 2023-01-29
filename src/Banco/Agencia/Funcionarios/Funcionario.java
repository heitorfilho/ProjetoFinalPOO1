package Banco.Agencia.Funcionarios;

import java.util.Scanner;

import Util.Data;
import Util.Endereco;
import Util.Pessoa;

public class Funcionario extends Pessoa {

    private String nroCarteiraTrabalho;
    private int rg_num; // numeros do RG
    private String rg_uf; // UF do RG
    private String cargo;
    private float salario;
    private int anoIngresso;

    public Funcionario(String nome, String cpf, Data dataNascimento, Endereco endereco, String sexo, String estadoCivil,
            String nroCarteiraTrabalho, int rg_num, String rg_uf, String cargo, float salario, int anoIngresso) {
        super(nome, cpf, dataNascimento, endereco, sexo, estadoCivil);
        this.nroCarteiraTrabalho = nroCarteiraTrabalho;
        this.rg_num = rg_num;
        this.rg_uf = rg_uf;
        this.cargo = cargo;
        this.salario = salario;
        this.anoIngresso = anoIngresso;
    }

    public Funcionario() {
        super(null, null, null, null, null, null);
    }

    public String getNroCarteiraTrabalho() {
        return this.nroCarteiraTrabalho;
    }

    public void setNroCarteiraTrabalho(String nroCarteiraTrabalho) {
        this.nroCarteiraTrabalho = nroCarteiraTrabalho;
    }

    public int getRg_num() {
        return this.rg_num;
    }

    public void setRg_num(int rg_num) {
        this.rg_num = rg_num;
    }

    public String getRg_uf() {
        return this.rg_uf;
    }

    public void setRg_uf(String rg_uf) {
        this.rg_uf = rg_uf;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return this.salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getAnoIngresso() {
        return this.anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

    public void printFunc() {
        System.out.println(this.getNome() + " - " + this.getCpf() + " - " + this.getCargo());
    }

    public String printFuncionario() {
        String data = printPessoa() + ";" + this.nroCarteiraTrabalho + ";" + this.rg_num + ";" + this.rg_uf + ";"
                + this.cargo + ";" + this.salario + ";" + this.anoIngresso;

        return data;
    }

    public String printGerente() { // caso funcioanrio que nao seja gerente queira acessar os dados do gerente
        throw new IllegalAccessError("Acesso negado!");
    }

    public void cadastraFuncionario() {
        super.cadastraPessoa();
        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("Digite o numero da carteira de trabalho: ");
            this.nroCarteiraTrabalho = sc.nextLine();
            System.out.println("Digite a UF do RG: ");
            this.rg_uf = sc.nextLine();
            System.out.println("Digite o numero do RG: ");
            this.rg_num = sc.nextInt();
            System.out.println("Digite o cargo: ");
            this.cargo = sc.nextLine();
            System.out.println("Digite o salario: ");
            this.salario = sc.nextFloat();
            System.out.println("Digite o ano de ingresso: ");
            this.anoIngresso = sc.nextInt();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void cadastraFuncionarioGerente() { // Para cadastrar um gerente nao eh necessario informar o cargo
        super.cadastraPessoa();
        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("Digite o numero da carteira de trabalho: ");
            this.nroCarteiraTrabalho = sc.nextLine();
            System.out.println("Digite a UF do RG: ");
            this.rg_uf = sc.nextLine();
            System.out.println("Digite o numero do RG: ");
            this.rg_num = sc.nextInt();
            this.cargo = "Gerente";
            System.out.println("Digite o salario: ");
            this.salario = sc.nextFloat();
            System.out.println("Digite o ano de ingresso: ");
            this.anoIngresso = sc.nextInt();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void calculaSalario() { // Se o funcionario estiver a mais de 15 anos na empresa, ele recebe um aumento
                                   // de 10%
        if ((2023 - this.anoIngresso) >= 15) {
            this.salario = this.salario + (this.salario * 0.1f);
        }
    }
}
