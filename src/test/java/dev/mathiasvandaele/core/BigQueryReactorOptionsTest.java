package dev.mathiasvandaele.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigQueryReactorOptionsTest {

    /**
     * This class contains tests for the `BigQueryReactorOptions` class.
     * We aim to test the `getDefaultOptions` method in this class
     * which is responsible for returning a `BigQueryReactorOptions` object
     * with pre-configured values for `HttpConnector`
     * and `GoogleAuthorizationManager`.
     */
    @Test
    void testGetDefaultOptions() throws IOException {
        // When
        BigQueryReactorOptions result = BigQueryReactorOptions.getDefaultOptions();

        assertEquals(result.getClass(), BigQueryReactorOptions.class);
        assertEquals(result.getConnector().getClass(), HttpConnector.class);
        assertEquals(result.getAuthorization().getClass(), GoogleAuthorizationManager.class);
    }

}