package dev.mathiasvandaele.models.bigqueryresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RequestResponse {
    private String kind;
    private Schema schema;
    private JobReference jobReference;
    private String totalRows;
    private List<Row> rows;
    private String totalBytesProcessed;
    private boolean jobComplete;
    private boolean cacheHit;
    private String queryId;
    private JobCreationReason jobCreationReason;
}
