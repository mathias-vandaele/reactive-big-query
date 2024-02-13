package dev.mathiasvandaele.core;

import dev.mathiasvandaele.domain.QueryOption;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class BigQueryRequest implements QueryOption {

    private String query;
    private boolean useLegacySql;

    @Override
    public String getQuery() {
        return query;
    }
}
