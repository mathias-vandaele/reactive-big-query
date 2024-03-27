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

@Builder
@ToString
public class BigQueryResult implements ServiceResult {

    private final List<FieldValueList> results;

    /**
     * Converts the results stored in the {@link BigQueryResult} to a {@link List} of objects of type T.
     *
     * @param <T>   the type of the objects to be converted to.
     * @param clazz the class object representing the type T.
     * @return a list of objects of type T.
     */
    @Override
    public <T extends Queryable<T>> List<T> toList(Class<T> clazz) {
        return null;
    }

    /**
     * Returns a single instance of the specified class by casting the FieldValueList to the specified type.
     * <p> Note: The method must be implemented by the library user.</p>
     *
     * @param clazz the class type to cast the FieldValueList
     * @param <T>   the type of the queryable object
     * @return a single instance of the specified class
     */
    @Override
    public <T extends Queryable<T>> T toSingle(Class<T> clazz) {
        if (results.isEmpty()) throw new EmptyResponseException("There is no result for this query, and thus, can not be casted into anything");
        return Try.of( () -> clazz.getDeclaredConstructor().newInstance())
                .getOrElseThrow( t -> new BigQueryCastingException("Developer issue: create a no args constructor for the Queryable class"))
                .cast(results.get(0));
    }

}
