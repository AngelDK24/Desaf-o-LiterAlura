package com.alura.screenmatch.excepcion;

public class ErorEnconvercionDeDuracionException extends RuntimeException {
    private String mesaje;
    public ErorEnconvercionDeDuracionException(String mesaje) {
        this.mesaje=mesaje;
    }
    @Override
    public String getMessage(){
        return this.mesaje;
    }
}
