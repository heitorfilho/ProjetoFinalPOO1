package Util;

public class Endereco {
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String cep;
    private String rua;
    private String numero;
    private String complemento;

    public Endereco(String pais, String estado, String cidade, String bairro, String cep, String rua, String numero, String complemento) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais){
        this.pais = pais;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep){
        this.cep = cep;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua){
        this.rua = rua;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento){
        this.complemento = complemento;
    }

    public String printEndereco(){
        String Data = rua + "; " + numero + "; " + complemento + "; " 
        + bairro + "; " + cidade + "; " + estado + "; " + pais + "; " + cep;
        return Data;
    }
}
