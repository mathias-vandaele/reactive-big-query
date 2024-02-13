package dev.mathiasvandaele.domain;

import reactor.core.publisher.Mono;

/**
 * The Connector interface defines the methods for executing a query.
 */
public interface Connector {

    /**
     * Executes a query with the specified options and authorization.
     *
     * @param requestOption The query options.
     * @param authorize The authorization details.
     * @return A Mono emitting a ServiceResult.
     */
    Mono<ServiceResult> executeQuery(QueryOption requestOption, Authorize authorize);
}
