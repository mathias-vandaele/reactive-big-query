package dev.mathiasvandaele.domain;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * The Authorize interface provides methods for obtaining request metadata and project ID.
 */
public interface Authorize {

    /**
     * Retrieves the request metadata as a Mono emitting a Map of header names to list of header values.
     * <p>Note: This returns a Mono cause this is a library supposed to work hands on hands with reactor
     * and this function must do async network calls </p>
     *
     * @return a Mono emitting a Map of header names to list of header values
     */
    Mono<Map<String, List<String>>> getRequestMetadata();

    /**
     * Retrieves the Google cloud project ID.
     *
     * @return the project ID
     */
    String getProjectId();
}
