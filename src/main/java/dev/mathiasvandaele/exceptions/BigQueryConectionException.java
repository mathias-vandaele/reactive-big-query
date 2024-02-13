package dev.mathiasvandaele.exceptions;

/**
 * Custom exception class to represent connection-related issues with BigQuery.
 */
public class BigQueryConectionException extends RuntimeException {
    public BigQueryConectionException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
