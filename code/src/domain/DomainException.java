package domain;

import java.io.FileNotFoundException;

public class DomainException extends RuntimeException {

    public DomainException(String message){
        super(message);
    }
    public DomainException(String message, FileNotFoundException ex){
        super(message);
    }
}
