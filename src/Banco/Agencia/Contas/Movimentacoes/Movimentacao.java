package Banco.Agencia.Contas.Movimentacoes;

import java.util.InputMismatchException;
import java.util.Scanner;

import Util.Data;

public class Movimentacao {

    private Data dataTransacao;
    private float valor;
    private String canalTransacao; // internet banking, caixa eletronico ou caixa fisico
    private String tipoTransacao; // saque, deposito, consultar saldo e efetuar pagamento
    private int numContaDestinatario;
    private int numAgenciaDestinatario;
    private int numBancoDestinatario;
    // private Conta contaAssociada; -> necessario ser uma conta corrente

    public Movimentacao(Data dataTransacao, float valor, String canalTransacao, String tipoTransacao,
            int numContaDestinatario,
            int numAgenciaDestinatario, int numBancoDestinatario) {
        this.dataTransacao = dataTransacao;
        this.valor = valor;
        this.canalTransacao = canalTransacao;
        this.tipoTransacao = tipoTransacao;
    }

    // Construtor default
    public Movimentacao() { // nao precisa de this, pois nao tem nenhum atributo com o mesmo nome
        dataTransacao = Data.dataAtual();
        valor = 0f;
        canalTransacao = "n/a"; // n/a = not applicable
        tipoTransacao = "n/a"; // n/a = not applicable
        numContaDestinatario = 0;
        numAgenciaDestinatario = 0;
        numBancoDestinatario = 0;
    }

    /////////////////////////////
    ///// GETTERS E SETTERS /////
    /////////////////////////////

    public Data getDataTransacao() {
        return this.dataTransacao;
    }

    public float getValor() {
        return this.valor;
    }

    public String getCanalTransacao() {
        return this.canalTransacao;
    }

    public String getTipoTransacao() {
        return this.tipoTransacao;
    }

    public int getNumContaDestinatario() {
        return this.numContaDestinatario;
    }

    public int getNumAgenciaDestinatario() {
        return this.numAgenciaDestinatario;
    }

    public void setNumBancoDestinatario(int numBancoDestinatario) {
        this.numBancoDestinatario = numBancoDestinatario;
    }

    public void setDataTransacao(Data dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setCanalTransacao(String canalTransacao) {
        this.canalTransacao = canalTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public void setNumContaDestinatario(int numContaDestinatario) {
        this.numContaDestinatario = numContaDestinatario;
    }

    public void setNumAgenciaDestinatario(int numAgenciaDestinatario) {
        this.numAgenciaDestinatario = numAgenciaDestinatario;
    }

    public int getNumBancoDestinatario() {
        return this.numBancoDestinatario;
    }

    ///////////////////////////
    ///// SAIDA DE DADOS /////
    /////////////////////////

    public String printMovimentacoes() {
        String data = dataTransacao.printData() + ";" + valor + ";" + canalTransacao + ";"
                + tipoTransacao + ";" + numContaDestinatario + ";" + numAgenciaDestinatario + ";"
                + numBancoDestinatario + ";";
        return data;
    }

    public void imprimeMovimentacoes() {
        System.out.printf("Data: %s -> Valor: %.2f -> Tipo: %s" + dataTransacao.printData(), valor,
                tipoTransacao);

    }

    /////////////////////////////
    ///// METODOS AUXILIARES /////
    /////////////////////////////

    public void TipoCanalTransacao() { // Metodo para descobrir qual eh o canal de transacao
        try (Scanner sc = new Scanner(System.in)) {
            int opcao = 4;
            while (opcao != 1 || opcao != 2 || opcao != 3) {
                System.out.println(
                        "Qual o canal da transacao? (1 - Internet Banking, 2 - Caixa Eletronico, 3 - Caixa Fisico)");
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

        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    /////////////////////////////
    /// TIPOS DE MOVIMENTACAO ///
    /////////////////////////////

    // Float eh um objeto, nao um tipo primitivo, que
    // pode ser null para representar um valor desconhecido
    // | _ |
    public void mov(Float valor) { // método auxiliar para criar movimentacao de qualquer tipo
        this.dataTransacao = Data.dataAtual();
        this.valor = valor;
        this.TipoCanalTransacao();
    }

    public void saque(Float valor) { // Cria movimentacao de saque
        mov(valor);
        this.tipoTransacao = "Saque";
    }

    public void deposito(Float valor) { // Cria movimentacao de deposito
        mov(valor);
        this.tipoTransacao = "Deposito";
    }

    public void consulta(Data dataTransacao) { // Cria movimentacao de consulta
        mov(valor);
        this.tipoTransacao = "Consulta";
    }

    public void pagamento(Float valor) { // Cria movimentacao de pagamento
        mov(valor);
        this.tipoTransacao = "Pagamento";
    }

    public void rendimento(Float valor) { // Cria movimentacao de rendimento
        mov(valor);
        this.tipoTransacao = "Rendimento";
    }

    public void transferencia(Float valor, int numBancoDestino, int numAgenciaDestino,
            int numContaDestino) { // Cria movimentacao de transferencia
        mov(valor);
        this.tipoTransacao = "Transferencia";
        this.numBancoDestinatario = numBancoDestino;
        this.numAgenciaDestinatario = numAgenciaDestino;
        this.numContaDestinatario = numContaDestino;
    }

    public void receberTransferencia(Float valor, int numBancoOrigem, int numAgenciaOrigem, int numContaOrigem) {
        mov(valor);
        this.tipoTransacao = "Receber Transferencia";
        this.numBancoDestinatario = numBancoOrigem;
        this.numAgenciaDestinatario = numAgenciaOrigem;
        this.numContaDestinatario = numContaOrigem;
    }
}
