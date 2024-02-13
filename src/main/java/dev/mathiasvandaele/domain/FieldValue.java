package dev.mathiasvandaele.domain;

import lombok.Builder;
import lombok.ToString;

/**
 * Represents a field and its corresponding value.
 */
@Builder
@ToString
public class FieldValue {
    private String key;
    private String value;
}
