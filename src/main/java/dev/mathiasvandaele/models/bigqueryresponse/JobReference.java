package dev.mathiasvandaele.models.bigqueryresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobReference {

    private String projectId;
    private String jobId;
    private String location;
}

