package dev.mathiasvandaele.exceptions;

/**
 * GoogleAuthorizationException is a custom RuntimeException class that is used to indicate an error or exception related to Google authorization.
 */
public class GoogleAuthorizationException extends RuntimeException {

    public GoogleAuthorizationException(String errorMessage, Throwable cause) {
       super(errorMessage, cause);
    }
}