package io.cody.all_relationships.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DomainConfigTest {

  /**
   * Method under test: {@link DomainConfig#dateTimeProvider()}
   */
  @Test
  void testDateTimeProvider() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: F009 Internal error.
    //   java.lang.NullPointerException: Cannot invoke "java.lang.Class.isArray()" because "<parameter1>" is null
    //   Please contact Diffblue through the appropriate support channel
    //   (https://www.diffblue.com/support) providing details about this error.

    assertTrue((new DomainConfig()).dateTimeProvider().getNow().isPresent());
  }
}

