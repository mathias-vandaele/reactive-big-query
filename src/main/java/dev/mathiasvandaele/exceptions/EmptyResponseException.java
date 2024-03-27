package dev.mathiasvandaele.exceptions;

public class EmptyResponseException extends RuntimeException{

    public EmptyResponseException(String message) {
        super(message);
    }
}
