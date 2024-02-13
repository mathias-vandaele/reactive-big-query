package dev.mathiasvandaele.exceptions;

/**
 * Custom exception class to represent an error while handling BigQuery response.
 */
public class BigQueryResponseException extends RuntimeException{
    public BigQueryResponseException(String responseAsString, Throwable cause) {
        super(responseAsString, cause);
    }
}
