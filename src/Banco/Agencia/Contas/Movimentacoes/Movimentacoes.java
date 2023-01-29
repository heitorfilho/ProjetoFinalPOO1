package Banco.Agencia.Contas.Movimentacoes;

import Util.Data;

public class Movimentacoes {

    private Data dataTransacao;
    private float valorTransacao;
    private String canalTransacao; // internet banking, caixa eletronico ou caixa fisico -> tambem pode fazer um metodo para descobrir qual eh o canal
    private String tipoTransacao; // saque, deposito, consultar saldo e efetuar pagamento

    public Movimentacoes(Data dataTransacao, float valorTransacao, String canalTransacao, String tipoTransacao) {
        this.dataTransacao = dataTransacao;
        this.valorTransacao = valorTransacao;
        this.canalTransacao = canalTransacao;
        this.tipoTransacao = tipoTransacao;
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

    /////////////////////////////
    /// TIPOS DE MOVIMENTACAO ///
    /////////////////////////////

    public void criaMovimentacaoSaque(Data dataTransacao, float valorTransacao, String canalTransacao) { //Cria movimentacao de saque
        this.dataTransacao = dataTransacao;
        this.valorTransacao = valorTransacao;
        this.canalTransacao = canalTransacao;
        this.tipoTransacao = "Saque";
    }

    public void criaMovimentacaoDeposito(Data dataTransacao, float valorTransacao, String canalTransacao) { //Cria movimentacao de deposito
        this.dataTransacao = dataTransacao;
        this.valorTransacao = valorTransacao;
        this.canalTransacao = canalTransacao;
        this.tipoTransacao = "Deposito";
    }

    public void criaMovimentacaoConsulta(Data dataTransacao, float valorTransacao, String canalTransacao) { //Cria movimentacao de consulta
        this.dataTransacao = dataTransacao;
        this.valorTransacao = valorTransacao;
        this.canalTransacao = canalTransacao;
        this.tipoTransacao = "Consulta";
    }

    public void criaMovimentacaoPagamento(Data dataTransacao, float valorTransacao, String canalTransacao) { //Cria movimentacao de pagamento
        this.dataTransacao = dataTransacao;
        this.valorTransacao = valorTransacao;
        this.canalTransacao = canalTransacao;
        this.tipoTransacao = "Pagamento";
    }

}
