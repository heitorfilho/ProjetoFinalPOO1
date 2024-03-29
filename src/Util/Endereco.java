package Util;

public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String complemento;
    private int cep;

    public Endereco(String rua, int numero, String bairro, String cidade, String estado, String pais, String complemento, int cep) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getRua(){
        return this.rua;
    }

    public int getNumero(){
        return this.numero;
    }

    public String getBairro(){
        return this.bairro;
    }

    public String getCidade(){
        return this.cidade;
    }

    public String getEstado(){
        return this.estado;
    }

    public String getPais(){
        return this.pais;
    }

    public String getComplemento(){
        return this.complemento;
    }

    public int getCep(){
        return this.cep;
    }

    public void setRua(String rua){
        this.rua = rua;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public void setPais(String pais){
        this.pais = pais;
    }

    public void setComplemento(String complemento){
        this.complemento = complemento;
    }

    public void setCep(int cep){
        this.cep = cep;
    }

    public String printEndereco() {
        String Data = rua + ";" + numero + ";" + bairro + ";"
                + cidade + ";" + estado + ";" + pais + ";" + complemento + ";" + cep + ";";
        return Data;
    }

}
