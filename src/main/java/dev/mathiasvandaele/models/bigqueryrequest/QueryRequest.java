package dev.mathiasvandaele.models.bigqueryrequest;

import dev.mathiasvandaele.models.bigqueryrequest.ConnectionProperty;
import dev.mathiasvandaele.models.bigqueryrequest.DefaultDataset;
import dev.mathiasvandaele.models.bigqueryrequest.FormatOptions;
import lombok.Builder;

import java.util.List;
@Builder
public class QueryRequest {
    public String kind;
    public String query;
    public String maxResults;
    public DefaultDataset defaultDataset;
    public int timeoutMs;
    public boolean dryRun;
    public boolean preserveNulls;
    public boolean useQueryCache;
    public boolean useLegacySql;
    public String parameterMode;
    public String location;
    public FormatOptions formatOptions;
    public List<ConnectionProperty> connectionProperties;
    public String maximumBytesBilled;
    public String requestId;
    public boolean createSession;
}
