package dev.mathiasvandaele.exceptions;

/**
 * The MissingElementFromBigQueryResponseException class is an exception class that is thrown when there is a missing element in the BigQuery response.
 */
public class MissingElementFromBigQueryResponseException extends RuntimeException {
    public MissingElementFromBigQueryResponseException(String errorMessage) {
        super(errorMessage);
    }
}
