package dev.mathiasvandaele.core;

import dev.mathiasvandaele.domain.QueryOption;
import dev.mathiasvandaele.domain.ServiceResult;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

/**
 * The BigQueryReactorClientTest tests the execute method of the BigQueryReactorClient class.
 */
public class BigQueryReactorClientTest {

    /**
     * Test for the execute method in the BigQueryReactorClient class.
     * Verifying that the query is executed with the correct parameters.
     */
    @Test
    public void testExecute() {
        // Arrange
        BigQueryReactorOptions options = mock(BigQueryReactorOptions.class);
        QueryOption requestOption = mock(QueryOption.class);
        ServiceResult expectedServiceResult = mock(ServiceResult.class);
        HttpConnector connector = mock(HttpConnector.class);
        GoogleAuthorizationManager googleAuthorizationManager = mock(GoogleAuthorizationManager.class);

        when(options.getAuthorization()).thenReturn(googleAuthorizationManager);
        when(options.getConnector()).thenReturn(connector);
        when(connector.executeQuery(requestOption, googleAuthorizationManager)).thenReturn(Mono.just(expectedServiceResult));
        
        BigQueryReactorClient client = BigQueryReactorClient.builder().options(options).build();

        // Act
        Mono<ServiceResult> resultMono = client.execute(requestOption);

        // Assert
        StepVerifier.create(resultMono)
                .expectNext(expectedServiceResult)
                .verifyComplete();
        verify(connector, times(1)).executeQuery(requestOption, googleAuthorizationManager);
    }
}