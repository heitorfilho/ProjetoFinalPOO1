package Banco.Agencia.Funcionarios;

import java.util.Scanner;

import Util.Data;
import Util.Endereco;
import Util.Pessoa;

public class Funcionario extends Pessoa {

    private int numCarteiraTrab;
    private String cargo;
    private float salario;
    private Data dataIngresso;
    private int rg_num; // numeros do RG
    private String rg_uf; // UF do RG
    private boolean salarioAjustado;

    public Funcionario(String nome, String cpf, Data dataNascimento, Endereco endereco, String sexo, String estadoCivil,
            int numCarteiraTrab, String cargo, float salario, Data dataIngresso, int rg_num, String rg_uf) {
        super(nome, cpf, dataNascimento, endereco, sexo, estadoCivil);
        this.numCarteiraTrab = numCarteiraTrab;
        this.cargo = cargo;
        this.salario = salario;
        this.rg_num = rg_num;
        this.rg_uf = rg_uf;
        this.dataIngresso = dataIngresso;
    }

    public Funcionario() {
        super(null, null, null, null, null, null);
    }

    public int getNumCarteiraTrab() {
        return this.numCarteiraTrab;
    }

    public String getCargo() {
        return this.cargo;
    }

    public float getSalario() {
        return this.salario;
    }

    public Data getDataIngresso() {
        return this.dataIngresso;
    }

    public int getRg_num() {
        return this.rg_num;
    }

    public String getRg_uf() {
        return this.rg_uf;
    }

    public boolean getSalarioAjustado(){
        return this.salarioAjustado;
    }

    public void setNumCarteiraTrab(int numCarteiraTrab) {
        this.numCarteiraTrab = numCarteiraTrab;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void setRg_num(int rg_num) {
        this.rg_num = rg_num;
    }

    public void setRg_uf(String rg_uf) {
        this.rg_uf = rg_uf;
    }

    public void setDataIngresso(Data dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public void printFunc() {
        System.out.println(this.getNome() + " ; " + this.getCpf() + " ; " + this.getCargo());
    }

    public String printFuncionario() {
        String data = printPessoa() + this.numCarteiraTrab + ";" + this.cargo + ";" + this.salario + ";"
                + this.dataIngresso + ";" + this.rg_num + ";" + this.rg_uf + ";";
        return data;
    }

    public String printGerente() throws IllegalAccessError { // Apenas um gerente pode imprimir os dados de gerente
        throw new IllegalAccessError("Acesso negado!");
    }

    public void cadastraFuncionario() {
        super.cadastraPessoa();
        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("Digite o numero da carteira de trabalho: ");
            this.numCarteiraTrab = sc.nextInt();
            System.out.println("Digite a UF do RG: ");
            this.rg_uf = sc.nextLine();
            System.out.println("Digite o numero do RG: ");
            this.rg_num = sc.nextInt();
            System.out.println("Digite o cargo: ");
            this.cargo = sc.nextLine();
            System.out.println("Digite o salario: ");
            this.salario = sc.nextFloat();
            System.out.println("Digite a data de ingresso: ");
            // this.dataIngresso = sc.next();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cadastraFuncionarioGerente() { // Para cadastrar um gerente nao eh necessario informar o cargo
        super.cadastraPessoa();
        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("Digite o numero da carteira de trabalho: ");
            this.numCarteiraTrab = sc.nextInt();
            System.out.println("Digite a UF do RG: ");
            this.rg_uf = sc.nextLine();
            System.out.println("Digite o numero do RG: ");
            this.rg_num = sc.nextInt();
            this.cargo = "Gerente";
            System.out.println("Digite o salario: ");
            this.salario = sc.nextFloat();
            System.out.println("Digite o ano de ingresso: ");
            // this.dataIngresso = sc.nextInt();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Se o funcionario estiver a mais de 15 anos na empresa, ele recebe um aumento
    // de 10% verificando se ja foi aumentado anteriormente
    public void calculaSalario() {
        if(this.salarioAjustado != true){
            if (this.dataIngresso.getAno() < 2008) {
            this.salario = this.salario * 1.1f;
            this.salarioAjustado = true;
            } else{
                System.out.println("Funcionario nao tem direito a aumento por tempo de servico!");
            }
        }
         else {
            System.out.println("Funcionario ja recebeu aumento por tempo de servico!");
        }
    }
}
