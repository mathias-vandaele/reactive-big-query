package dev.mathiasvandaele.domain;

import lombok.ToString;

/**
 * The QueryOption interface represents an option for a query and defines a method to get the query string.
 */
public interface QueryOption {
    /**
     * Retrieves the query string.
     *
     * @return The query string.
     */
    String getQuery();
}
