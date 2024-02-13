package dev.mathiasvandaele.domain;

/**
 * Represents a queryable object.
 *
 * @param <T> the type of the queryable object.
 */
public interface Queryable<T extends Queryable<T>> {

    /**
     * Casts the given FieldValue to the specific type.
     * <p> Note: Must be implemented by the library user</p>
     *
     * @param fieldValue the FieldValue to be cast
     * @return the cast value of the specified type
     */
    T cast(FieldValue fieldValue);

}
