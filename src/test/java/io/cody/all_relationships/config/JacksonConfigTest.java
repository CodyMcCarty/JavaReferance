package io.cody.all_relationships.config;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

class JacksonConfigTest {

  /**
   * Method under test: {@link JacksonConfig#jacksonCustomizer()}
   */
  @Test
  void testJacksonCustomizer() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R004 No meaningful assertions found.
    //   Diffblue Cover was unable to create an assertion.
    //   Make sure that fields modified by jacksonCustomizer()
    //   have package-private, protected, or public getters.
    //   See https://diff.blue/R004 to resolve this issue.

    // TODO: Complete this test.
    //   Reason: R004 No meaningful assertions found.
    //   Diffblue Cover was unable to create an assertion.
    //   Make sure that fields modified by jacksonCustomizer()
    //   have package-private, protected, or public getters.
    //   See https://diff.blue/R004 to resolve this issue.

    Jackson2ObjectMapperBuilderCustomizer actualJacksonCustomizerResult = (new JacksonConfig()).jacksonCustomizer();
    actualJacksonCustomizerResult.customize(Jackson2ObjectMapperBuilder.json());
  }

  /**
   * Method under test: {@link JacksonConfig#jacksonCustomizer()}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testJacksonCustomizer2() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R004 No meaningful assertions found.
    //   Diffblue Cover was unable to create an assertion.
    //   Make sure that fields modified by jacksonCustomizer()
    //   have package-private, protected, or public getters.
    //   See https://diff.blue/R004 to resolve this issue.

    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.featuresToDisable(Object[])" because "jacksonObjectMapperBuilder" is null
    //       at io.cody.all_relationships.config.JacksonConfig.lambda$jacksonCustomizer$0(JacksonConfig.java:16)
    //   In order to prevent jacksonCustomizer()
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   jacksonCustomizer().
    //   See https://diff.blue/R013 to resolve this issue.

    (new JacksonConfig()).jacksonCustomizer().customize(null);
  }

  /**
   * Method under test: {@link JacksonConfig#jacksonCustomizer()}
   */
  @Test
  void testJacksonCustomizer3() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R004 No meaningful assertions found.
    //   Diffblue Cover was unable to create an assertion.
    //   Make sure that fields modified by jacksonCustomizer()
    //   have package-private, protected, or public getters.
    //   See https://diff.blue/R004 to resolve this issue.

    Jackson2ObjectMapperBuilderCustomizer actualJacksonCustomizerResult = (new JacksonConfig()).jacksonCustomizer();
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = mock(
        Jackson2ObjectMapperBuilder.class);
    when(jackson2ObjectMapperBuilder.featuresToDisable((Object[]) any()))
        .thenReturn(Jackson2ObjectMapperBuilder.json());
    actualJacksonCustomizerResult.customize(jackson2ObjectMapperBuilder);
    verify(jackson2ObjectMapperBuilder).featuresToDisable((Object[]) any());
  }
}

