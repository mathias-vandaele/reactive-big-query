package dev.mathiasvandaele.domain;

import reactor.core.publisher.Mono;

/**
 * The Service interface represents a service that executes queries with the specified options.
 */
public interface Service{

    /**
     * Executes a query with the specified options.
     *
     * @param requestOption The query options.
     * @return A Mono emitting a ServiceResult.
     */
    Mono<ServiceResult> execute(QueryOption requestOption);

}
