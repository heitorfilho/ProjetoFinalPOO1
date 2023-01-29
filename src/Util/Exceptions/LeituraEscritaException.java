package Util.Exceptions;

import java.io.IOException;

public class LeituraEscritaException extends IOException {
    public LeituraEscritaException(String msg, Throwable err){
        super(msg);
    }
}
