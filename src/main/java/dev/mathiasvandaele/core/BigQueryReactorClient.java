package dev.mathiasvandaele.core;

import dev.mathiasvandaele.domain.QueryOption;
import dev.mathiasvandaele.domain.Service;
import dev.mathiasvandaele.domain.ServiceResult;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * The BigQueryReactorClient class is responsible for executing queries on Google BigQuery.
 * It implements the Service interface and provides a method to execute the queries.
 */
@Builder
@Slf4j
public class BigQueryReactorClient implements Service {

    private final BigQueryReactorOptions options;

    /**
     * Executes a query with the specified options.
     *
     * @param requestOption The query options.
     * @return A Mono emitting a ServiceResult.
     */
    @Override
    public Mono<ServiceResult> execute(QueryOption requestOption) {
        log.debug("Executing the request with option: {}", requestOption.toString());
        return options.getConnector()
                .executeQuery(requestOption, options.getAuthorization());
    }
}

