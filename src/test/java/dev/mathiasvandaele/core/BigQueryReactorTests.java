package dev.mathiasvandaele.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * The BigQueryReactorTest class provides unit tests for the BigQueryReactor class.
 * Specifically, it tests the 'create' method which is responsible for creating an instance of BigQueryReactorClient.
 * Each test focuses on a single use case contributing to code clarity and readability.
 */
public class BigQueryReactorTests {

  /**
   * In this test case, we check if the method successfully creates an instance of BigQueryReactorClient.
   */
  @Test
  public void create_returnsInstance() {
    BigQueryReactorOptions options = Mockito.mock(BigQueryReactorOptions.class);

    BigQueryReactorClient result = BigQueryReactor.create(options);

    assertNotNull(result);
    assertEquals(BigQueryReactorClient.class, result.getClass());
  }

}