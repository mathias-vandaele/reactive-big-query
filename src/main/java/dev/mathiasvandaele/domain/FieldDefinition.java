package dev.mathiasvandaele.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FieldDefinition {
    private String name;
    private String type;
    private String mode;
}
