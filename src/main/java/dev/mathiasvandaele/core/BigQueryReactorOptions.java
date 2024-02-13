package dev.mathiasvandaele.core;

import com.google.auth.oauth2.GoogleCredentials;
import dev.mathiasvandaele.domain.Authorize;
import dev.mathiasvandaele.domain.Connector;
import dev.mathiasvandaele.domain.ServiceOptions;
import lombok.Builder;
import lombok.ToString;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;

/**
 * The BigQueryReactorOptions class represents the configuration options for the BigQueryReactorClient.
 */
@Builder
@ToString
public class BigQueryReactorOptions implements ServiceOptions {

    protected static final String DEFAULT_HOST = "https://bigquery.googleapis.com";
    protected static final String ENDPOINT = "/bigquery/v2/projects/{projectId}/queries";

    private HttpConnector httpConnector;
    private GoogleAuthorizationManager googleAuthorizationManager;

    /**
     * This method returns the default options for the BigQueryReactorClient. It builds and returns a BigQueryReactorOptions object with pre-configured values for the HttpConnector
     * and GoogleAuthorizationManager.
     *
     * @return A BigQueryReactorOptions object with default options.
     * @throws IOException if there is an error retrieving the default GoogleCredentials.
     */
    public static BigQueryReactorOptions getDefaultOptions() throws IOException {
        return BigQueryReactorOptions.builder()
                .httpConnector(HttpConnector.builder()
                        .httpClient(HttpClient.create()
                                .baseUrl(DEFAULT_HOST))
                        .build())
                .googleAuthorizationManager(GoogleAuthorizationManager.builder()
                        .credentialsManager(GoogleCredentials.getApplicationDefault())
                        .build())
                .build();
    }

    @Override
    public Connector getConnector() {
        return this.httpConnector;
    }

    @Override
    public Authorize getAuthorization() {
        return this.googleAuthorizationManager;
    }
}
