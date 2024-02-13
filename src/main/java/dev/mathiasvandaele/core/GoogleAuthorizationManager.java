package dev.mathiasvandaele.core;

import com.google.auth.oauth2.GoogleCredentials;
import dev.mathiasvandaele.domain.Authorize;
import dev.mathiasvandaele.exceptions.GoogleAuthorizationException;
import io.vavr.control.Try;
import lombok.Builder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Map;

/**
 * The GoogleAuthorizationManager class is an implementation of the Authorize interface. It provides methods for obtaining request metadata and Google Cloud project ID using Google
 * Credentials.
 */
@Builder
public class GoogleAuthorizationManager implements Authorize {

    GoogleCredentials credentialsManager;

    /**
     * Retrieves the request metadata as a Mono emitting a Map of header names to list of header values.
     * <p>Note: This returns a Mono cause this is a library supposed to work hands on hands with reactor
     * and this function must do async network calls </p>
     *
     * @return a Mono emitting a Map of header names to list of header values
     * @throws GoogleAuthorizationException if the token could not be retrieved
     */
    @Override
    public Mono<Map<String, List<String>>> getRequestMetadata() {
        return Mono.fromSupplier(() -> Try.of(() -> this.credentialsManager.getRequestMetadata())
                        .getOrElseThrow((cause) -> new GoogleAuthorizationException("The token could no be retrieved, Have you tried `gcloud auth application-default login`?", cause)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * Retrieves the Google Cloud project ID.
     *
     * @return the project ID
     */
    @Override
    public String getProjectId() {
        return this.credentialsManager.getQuotaProjectId();
    }
}
