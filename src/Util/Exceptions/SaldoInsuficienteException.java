package Util.Exceptions;

public class SaldoInsuficienteException extends IllegalStateException{
    public SaldoInsuficienteException(String msg){
        super(msg);
    }
}
