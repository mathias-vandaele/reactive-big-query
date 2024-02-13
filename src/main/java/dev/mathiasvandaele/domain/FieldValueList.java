package dev.mathiasvandaele.domain;

import lombok.Builder;
import lombok.ToString;

import java.util.List;

/**
 * Represents a list of field values.
 */
@Builder
@ToString
public class FieldValueList {

    private final List<FieldValue> fieldValues;
}
