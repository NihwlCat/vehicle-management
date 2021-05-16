package br.pedro.program.services.exceptions;

public class DataErrorException extends RuntimeException{
    public DataErrorException(String msg){
        super(msg);
    }
}
