package dev.mathiasvandaele.domain;

import lombok.Getter;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class FieldList {

    private final Map<String, Integer> nameIndex;
    private final List<FieldDefinition> fieldDefinitions;

    public FieldList(List<FieldDefinition> fieldDefinitions) {
        this.fieldDefinitions = fieldDefinitions;
        this.nameIndex = fieldDefinitions.stream().collect( Collectors.toMap(FieldDefinition::getName, fieldDefinitions::indexOf));
    }
}
