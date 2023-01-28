package Banco.Agencia.Funcionarios;


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

    public Funcionario(String nome, String cpf, String sexo, Data dataNascimento, Endereco endereco, String estadoCivil,
            String nroCarteiraTrabalho, int rg_num, String rg_uf, String cargo, float salario, int anoIngresso) {
        super(nome, cpf, sexo, dataNascimento, endereco, estadoCivil);
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

    public String printFuncionario() {
        String data = printPessoa() + ";" + this.nroCarteiraTrabalho + ";" + this.rg_num + ";" + this.rg_uf + ";"
                + this.cargo + ";" + this.salario + ";" + this.anoIngresso;

        return data;
    }

    public void printTodosFunc(){
        System.out.println(this.getNome() + " - " + this.getCpf() +  " - " + this.getCargo());
    }

    public String printGerente(){
        throw new IllegalAccessError("Acesso negado!");
    }
}
