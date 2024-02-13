package dev.mathiasvandaele.core;

import lombok.extern.slf4j.Slf4j;

/**
 * The BigQueryReactor class provides a utility method to create an instance of BigQueryReactorClient.
 */
@Slf4j
public class BigQueryReactor {

    public static BigQueryReactorClient create(BigQueryReactorOptions options) {
        log.debug("Creating the `BigQueryReactorClient` with options: {}", options );
        return new BigQueryReactorClient(options);
    }

}
