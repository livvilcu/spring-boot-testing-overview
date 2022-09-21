package com.stratospheric.testingoverview.service;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// register the Mokito JUnit Jupiter extension
@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {

  /**
   * JUnit is the testing framework to launch tests on the JVM.
   * Mockito is the de-facto standard mocking framework for Java projects.
   * Pick one assertion library for writing tests: JUnit's built-in assertions, Hamcrest or AssertJ.
   * Both JSONassert and JsonPath help writing tests for JSON data structures.
   */
  @Mock
  private ProductVerifierService mockedProductVerifier;
  @Mock
  private ProductReporterService mockedProductReporter;
  @Test
  void shouldReturnCheapPriceWhenProductIsInStockOfCompetitor() {
    //Specify what boolean value to return for this test
    when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods")).thenReturn(true);

    PricingService classUnderTest = new PricingService(mockedProductVerifier, mockedProductReporter);

    // JUnit
    assertEquals(new BigDecimal("99.99"), classUnderTest.calculatePrice("AirPods"));

    verify(mockedProductReporter).notify("AirPods");

    // Hamcrest: contains(), isEmpty(), hasSize()
    assertThat(classUnderTest.calculatePrice("AirPods"), equalTo(new BigDecimal("99.99")));

    // AssertJ
    assertThat(classUnderTest.calculatePrice("AirPods")).isEqualTo(new BigDecimal("99.99"));
  }


  /**
   * It is recommended that you leave strictMode off, so your tests will be less brittle.
   * Turn it on if you need to enforce a particular order for arrays, or if you want to ensure
   * that the actual JSON does not have any fields beyond what's expected.
   *
   * @throws JSONException
   */
  @Test
  void jsonAssertExample() throws JSONException {
    String result = "{\"name\": \"duke\", \"age\":\"42\"}";
    JSONAssert.assertEquals("{\"name\": \"duke\"}", result, false);

    JSONAssert.assertNotEquals("{\"name\": \"duke\"}", result, true);
  }

  @Test
  void jsonPathExample() {
    String result = "{\"age\":\"42\", \"name\": \"duke\", \"tags\":[\"java\", \"jdk\"]}";

    // Using JUnit 5 Assertions
    assertEquals(2, JsonPath.parse(result).read("$.tags.length()", Long.class));
    assertEquals("duke", JsonPath.parse(result).read("$.name", String.class));
  }


}
