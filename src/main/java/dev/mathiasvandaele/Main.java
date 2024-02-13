package dev.mathiasvandaele;

import dev.mathiasvandaele.core.*;
import dev.mathiasvandaele.domain.QueryOption;
import reactor.core.Disposable;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        BigQueryReactorClient bigQueryReactor = BigQueryReactor.create(BigQueryReactorOptions.getDefaultOptions());

        QueryOption request = BigQueryRequest.builder()
                .query("SELECT dteday, windspeed, hum FROM `healthy-mark-413518.bike_sharing.bike sharing` LIMIT 2")
                .useLegacySql(false)
                .build();

        bigQueryReactor.execute(request)
                .subscribe(System.out::println);

        Thread.sleep(10000);

    }
}