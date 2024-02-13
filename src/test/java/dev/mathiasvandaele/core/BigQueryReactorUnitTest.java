package dev.mathiasvandaele.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * The BigQueryReactorUnitTest class is used to check
 * the functionality of the BigQueryReactor's create method.
 */
public class BigQueryReactorUnitTest {

  /**
   * This test method ensures BigQueryReactor's create method generates
   * a new instance of BigQueryReactorClient when provided with a valid
   * instance of BigQueryReactorOptions.
   */
  @Test
  void create_whenGivenValidOptions_ShouldReturnNewBigQueryReactorClient() {
    BigQueryReactorOptions options = mock(BigQueryReactorOptions.class);

    BigQueryReactorClient client = BigQueryReactor.create(options);

    assertNotNull(client, "Expected a new instance of BigQueryReactorClient");
  }
}