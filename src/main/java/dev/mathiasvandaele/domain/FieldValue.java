package dev.mathiasvandaele.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a field and its corresponding value.
 */
@Builder
@ToString
@Getter
public class FieldValue{
    private String key;
    private String value;
}
