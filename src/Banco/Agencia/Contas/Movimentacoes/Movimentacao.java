package Banco.Agencia.Contas.Movimentacoes;

import java.util.Scanner;

import Util.Data;

public class Movimentacao {

    private Data dataTransacao;
    private float valorTransacao;
    private String canalTransacao; // internet banking, caixa eletronico ou caixa fisico -> tambem pode fazer um metodo para descobrir qual eh o canal
    private String tipoTransacao; // saque, deposito, consultar saldo e efetuar pagamento

    public Movimentacao(Data dataTransacao, float valorTransacao, String canalTransacao, String tipoTransacao) {
        this.dataTransacao = dataTransacao;
        this.valorTransacao = valorTransacao;
        this.canalTransacao = canalTransacao;
        this.tipoTransacao = tipoTransacao;
    }

    public Movimentacao() {
        this.valorTransacao = 0;
        this.canalTransacao = "";
        this.tipoTransacao = "";
    }

    /////////////////////////////
    ///// GETTERS E SETTERS /////
    /////////////////////////////

    public Data getDataTransacao() {
        return this.dataTransacao;
    }

    public float getValorTransacao() {
        return this.valorTransacao;
    }

    public String getCanalTransacao() {
        return this.canalTransacao;
    }

    public String getTipoTransacao() {
        return this.tipoTransacao;
    }

    public void setDataTransacao(Data dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public void setValorTransacao(float valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public void setCanalTransacao(String canalTransacao) {
        this.canalTransacao = canalTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
    
    public String printMovimentacoes() {
        String data = this.dataTransacao + ";" + this.valorTransacao + ";" + this.canalTransacao + ";" + this.tipoTransacao;
        return data;
    }

    public void TipoCanalTransacao(){ //Metodo para descobrir qual eh o canal de transacao
        try(Scanner sc = new Scanner(System.in)){
            int opcao = 4;
            while (opcao != 1 || opcao != 2 || opcao != 3) {
                System.out.println("Qual o canal de saque? (1 - Internet Banking, 2 - Caixa Eletronico, 3 - Caixa Fisico)");
                opcao = sc.nextInt();
                switch (opcao) {
                    case 1:
                        this.canalTransacao = "Internet Banking";
                        break;
                    case 2:
                        this.canalTransacao = "Caixa Eletronico";
                        break;
                    case 3:
                        this.canalTransacao = "Caixa Fisico";
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /////////////////////////////
    /// TIPOS DE MOVIMENTACAO ///
    /////////////////////////////

    public void saque(float valorTransacao, Data dataTransacao) { //Cria movimentacao de saque
        this.valorTransacao = valorTransacao;
        this.dataTransacao = dataTransacao;
        this.TipoCanalTransacao(); 
        this.tipoTransacao = "Saque";
    }

    public void deposito(float valorTransacao, Data dataTransacao) { //Cria movimentacao de deposito
        this.valorTransacao = valorTransacao;
        this.dataTransacao = dataTransacao;
        this.TipoCanalTransacao(); 
        this.tipoTransacao = "Deposito";
    }

    public void consulta(Data dataTransacao) { //Cria movimentacao de consulta
        this.valorTransacao = 0;
        this.dataTransacao = dataTransacao;
        this.TipoCanalTransacao(); 
        this.tipoTransacao = "Consulta";
    }

    public void pagamento(float valorTransacao, Data dataTransacao) { //Cria movimentacao de pagamento
        this.valorTransacao = valorTransacao;
        this.dataTransacao = dataTransacao;
        this.TipoCanalTransacao(); 
        this.tipoTransacao = "Pagamento";
    }

    public void transferencia(float valorTransacao, Data dataTransacao) { //Cria movimentacao de transferencia
        this.valorTransacao = valorTransacao;
        this.dataTransacao = dataTransacao;
        this.TipoCanalTransacao(); 
        this.tipoTransacao = "Transferencia";
    }

    public void receberTransferencia(float valorTransacao, Data dataTransacao) { //Cria movimentacao de receber transferencia
        this.valorTransacao = valorTransacao;
        this.dataTransacao = dataTransacao;
        this.TipoCanalTransacao(); 
        this.tipoTransacao = "Receber Transferencia";
    }

}
