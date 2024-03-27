package dev.mathiasvandaele.models.bigqueryresponse;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Field {
    private String name;
    private String type;
    private String mode;

}
