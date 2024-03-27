package dev.mathiasvandaele.core;

import dev.mathiasvandaele.domain.FieldList;
import dev.mathiasvandaele.domain.FieldValueList;
import dev.mathiasvandaele.domain.Queryable;
import dev.mathiasvandaele.domain.ServiceResult;
import dev.mathiasvandaele.exceptions.BigQueryCastingException;
import dev.mathiasvandaele.exceptions.EmptyResponseException;
import io.vavr.control.Try;
import lombok.Builder;
import lombok.ToString;

import java.util.List;
import java.util.stream.StreamSupport;

@Builder
@ToString
public class BigQueryResult implements ServiceResult {

    private final List<FieldValueList> results;

    /**
     * Converts the stored results of a BigQuery query into a list of objects of type T.
     *
     * @param <T>   the type parameter representing the queryable object.
     * @param clazz the class object representing the type T.
     * @return a list of objects of type T based on the stored results.
     * @throws EmptyResponseException if there are no results in the BigQueryResult. It indicates that the query did not return any results.
     */
    @Override
    public <T extends Queryable<T>> List<T> toList(Class<T> clazz) {
        if (results.isEmpty())
            throw new EmptyResponseException("There is no result for this query, and thus, can not be casted into anything");
        return results.stream()
                .map(fieldValueList -> this.createInstance(clazz, fieldValueList))
                .toList();
    }

    /**
     * Retrieves a single object of type T from the results stored in the BigQueryResult.
     *
     * @param <T>   the type of the object to be retrieved.
     * @param clazz the class object representing the type T.
     * @return an object of type T.
     * @throws EmptyResponseException   if there are no results in the BigQueryResult.
     *                                  It indicates that the query did not return any results.
     * @throws BigQueryCastingException if the provided class does not have a no-args constructor.
     *                                  The constructor is required for creating an instance of the class.
     */
    @Override
    public <T extends Queryable<T>> T toSingle(Class<T> clazz) {
        if (results.isEmpty())
            throw new EmptyResponseException("There is no result for this query, and thus, can not be casted into anything");
        return createInstance(clazz, this.results.get(0));
    }

    /**
     * Creates an instance of the given class and casts it to the specified type using the FieldValueList.
     * The class must have a no-args constructor.
     *
     * @param <T>            the type of the queryable object
     * @param clazz          the class object representing the type T
     * @param fieldValueList the FieldValueList to be cast
     * @return an instance of the specified class cast to the specified type
     * @throws BigQueryCastingException if the class does not have a no-args constructor
     */
    private <T extends Queryable<T>> T createInstance(Class<T> clazz, FieldValueList fieldValueList) {
        return Try.of(() -> clazz.getDeclaredConstructor().newInstance())
                .getOrElseThrow(t -> new BigQueryCastingException("Developer issue: create a no args constructor for the Queryable class"))
                .cast(fieldValueList);
    }

}
