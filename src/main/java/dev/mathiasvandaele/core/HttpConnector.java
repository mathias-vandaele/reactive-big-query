package dev.mathiasvandaele.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mathiasvandaele.domain.*;
import dev.mathiasvandaele.exceptions.BigQueryConectionException;
import dev.mathiasvandaele.exceptions.BigQueryResponseException;
import dev.mathiasvandaele.exceptions.MissingElementFromBigQueryResponseException;
import dev.mathiasvandaele.models.bigqueryrequest.QueryRequest;
import dev.mathiasvandaele.models.bigqueryresponse.Field;
import dev.mathiasvandaele.models.bigqueryresponse.RequestResponse;
import dev.mathiasvandaele.models.bigqueryresponse.Schema;
import lombok.Builder;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * The HttpConnector class is an implementation of the Connector interface. It provides methods for executing a query using HTTP connection.
 */
@Builder
public class HttpConnector implements Connector {

    private final GoogleAuthorizationManager credentialsManager;
    private final HttpClient httpClient;
    public final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Executes a query with the specified options and authorization.
     *
     * @param requestOption The query options.
     * @param authorize The authorization details.
     * @return A Mono emitting a ServiceResult.
     */
    @Override
    public Mono<ServiceResult> executeQuery(QueryOption requestOption, Authorize authorize) {
        return authorize.getRequestMetadata()
                .flatMap(stringListMap -> httpClient.headers(entries -> stringListMap.forEach(entries::add))
                        .post()
                        .uri(String.format("/bigquery/v2/projects/%s/queries", authorize.getProjectId()))
                        .send((httpClientRequest, nettyOutbound) -> nettyOutbound.sendString(this.createQuery(requestOption)))
                        .responseSingle((response, byteBufMono) -> byteBufMono.asString())
                        .onErrorMap( cause -> new BigQueryConectionException("Something wrong has happened while trying to retrieve the data from the server", cause))
                        .map(this::createResponse)
                        .map(this::createServiceResult));
    }

    /**
     * Creates a {@link ServiceResult} object based on the given {@link RequestResponse}.
     *
     * @param requestResponse The request response.
     * @return A {@link ServiceResult} object.
     * @throws MissingElementFromBigQueryResponseException If there is a missing element in the BigQuery response.
     */
    private ServiceResult createServiceResult(RequestResponse requestResponse) {

        List<Field> fields = Optional.of(requestResponse)
                .map(RequestResponse::getSchema)
                .map(Schema::getFields)
                .orElseThrow(() -> new MissingElementFromBigQueryResponseException("Please, there must have been a breaking change in google big query api."));

        return Optional.ofNullable(requestResponse.getRows())
                .map(Collection::stream)
                .map(rowStream -> rowStream.map(row -> IntStream.range(0, fields.size())
                        .mapToObj(index -> FieldValue.builder()
                                .key(fields.get(index).getName())
                                .value(row.getF().get(index).getV())
                                .build())
                        .toList()))
                .map(listStream -> listStream.map(fieldValues -> FieldValueList.builder()
                                .fieldValues(fieldValues)
                                .build())
                        .toList())
                .map(fieldValueLists -> BigQueryResult.builder()
                        .results(fieldValueLists)
                        .build())
                .orElseGet(() -> BigQueryResult.builder()
                        .results(List.of())
                        .build());
    }

    /**
     * Create a RequestResponse object from a JSON response string.
     *
     * @param response The JSON response string.
     * @return The created RequestResponse object.
     * @throws BigQueryResponseException if an error occurs while parsing the JSON response.
     */
    private RequestResponse createResponse(String response) {
        try {
            return this.objectMapper.readValue(response, RequestResponse.class);
        } catch (JsonProcessingException e) {
            throw new BigQueryResponseException(response, e);
        }
    }

    /**
     * Create a query string based on the provided request option.
     *
     * @param requestOption The request option containing the query string.
     * @return A publisher emitting the query string as a string.
     */
    private Publisher<String> createQuery(QueryOption requestOption) {
        return Mono.fromCallable(() -> this.objectMapper
                .writeValueAsString(QueryRequest.builder()
                        .query(requestOption.getQuery())
                        .dryRun(false)
                        .build()));
    }


}
