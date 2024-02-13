package dev.mathiasvandaele.models.bigqueryresponse;

import dev.mathiasvandaele.models.bigqueryresponse.F;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Row {
    private List<F> f;
}
