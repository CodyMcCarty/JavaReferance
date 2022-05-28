package io.cody.all_relationships.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.cody.all_relationships.model.ErrorResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

class RestExceptionHandlerTest {

  /**
   * Method under test: {@link RestExceptionHandler#handleNotFound(ResponseStatusException)}
   */
  @Test
  void testHandleNotFound() {
    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
    ResponseEntity<ErrorResponse> actualHandleNotFoundResult = restExceptionHandler
        .handleNotFound(new ResponseStatusException(HttpStatus.CONTINUE));
    assertTrue(actualHandleNotFoundResult.hasBody());
    assertTrue(actualHandleNotFoundResult.getHeaders().isEmpty());
    assertEquals(HttpStatus.CONTINUE, actualHandleNotFoundResult.getStatusCode());
    ErrorResponse body = actualHandleNotFoundResult.getBody();
    assertEquals("ResponseStatusException", body.getException());
    assertEquals("100 CONTINUE", body.getMessage());
    assertEquals(100, body.getHttpStatus().intValue());
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleNotFound(org.springframework.web.server.ResponseStatusException)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testHandleNotFound2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.springframework.web.server.ResponseStatusException.getStatus()" because "exception" is null
    //       at io.cody.all_relationships.config.RestExceptionHandler.handleNotFound(RestExceptionHandler.java:25)
    //   In order to prevent handleNotFound(ResponseStatusException)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   handleNotFound(ResponseStatusException).
    //   See https://diff.blue/R013 to resolve this issue.

    (new RestExceptionHandler()).handleNotFound(null);
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleNotFound(org.springframework.web.server.ResponseStatusException)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testHandleNotFound3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.lang.reflect.Executable.toGenericString()" because the return value of "org.springframework.core.MethodParameter.getExecutable()" is null
    //       at org.springframework.web.bind.support.WebExchangeBindException.getMessage(WebExchangeBindException.java:289)
    //       at io.cody.all_relationships.config.RestExceptionHandler.handleNotFound(RestExceptionHandler.java:27)
    //   In order to prevent handleNotFound(ResponseStatusException)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   handleNotFound(ResponseStatusException).
    //   See https://diff.blue/R013 to resolve this issue.

    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
    MethodParameter parameter = new MethodParameter(
        new MethodParameter(new MethodParameter(new MethodParameter(mock(MethodParameter.class)))));
    restExceptionHandler.handleNotFound(new WebExchangeBindException(parameter,
        new BindException(
            new BindException(new BindException(new BindException(mock(BindingResult.class)))))));
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleNotFound(org.springframework.web.server.ResponseStatusException)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testHandleNotFound4() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.lang.reflect.Executable.toGenericString()" because the return value of "org.springframework.core.MethodParameter.getExecutable()" is null
    //       at org.springframework.web.bind.support.WebExchangeBindException.getMessage(WebExchangeBindException.java:289)
    //       at io.cody.all_relationships.config.RestExceptionHandler.handleNotFound(RestExceptionHandler.java:27)
    //   In order to prevent handleNotFound(ResponseStatusException)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   handleNotFound(ResponseStatusException).
    //   See https://diff.blue/R013 to resolve this issue.

    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
    BindingResult bindingResult = mock(BindingResult.class);
    doNothing().when(bindingResult).addAllErrors((org.springframework.validation.Errors) any());

    BindException bindException = new BindException(bindingResult);
    bindException.addAllErrors(
        new BindException(
            new BindException(new BindException(new BindException(mock(BindingResult.class))))));
    BindException bindingResult1 = new BindException(
        new BindException(new BindException(bindException)));
    restExceptionHandler.handleNotFound(new WebExchangeBindException(
        new MethodParameter(new MethodParameter(
            new MethodParameter(new MethodParameter(mock(MethodParameter.class))))),
        bindingResult1));
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException)}
   */
  @Test
  void testHandleMethodArgumentNotValid() {
    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
    BindingResult bindingResult = mock(BindingResult.class);
    ArrayList<org.springframework.validation.FieldError> fieldErrorList = new ArrayList<>();
    when(bindingResult.getFieldErrors()).thenReturn(fieldErrorList);
    BindException bindingResult1 = new BindException(
        new BindException(new BindException(new BindException(new BindException(
            new BindException(
                new BindException(new BindException(new BindException(bindingResult)))))))));
    ResponseEntity<ErrorResponse> actualHandleMethodArgumentNotValidResult = restExceptionHandler
        .handleMethodArgumentNotValid(new MethodArgumentNotValidException(
            new MethodParameter(
                new MethodParameter(
                    new MethodParameter(new MethodParameter(mock(MethodParameter.class))))),
            bindingResult1));
    assertTrue(actualHandleMethodArgumentNotValidResult.hasBody());
    assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
    assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
    ErrorResponse body = actualHandleMethodArgumentNotValidResult.getBody();
    assertEquals("MethodArgumentNotValidException", body.getException());
    assertEquals(fieldErrorList, body.getFieldErrors());
    assertEquals(400, body.getHttpStatus().intValue());
    verify(bindingResult).getFieldErrors();
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException)}
   */
  @Test
  void testHandleMethodArgumentNotValid2() {
    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

    ArrayList<org.springframework.validation.FieldError> fieldErrorList = new ArrayList<>();
    fieldErrorList.add(
        new org.springframework.validation.FieldError("Object Name", "Field", "Default Message"));
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.getFieldErrors()).thenReturn(fieldErrorList);
    BindException bindingResult1 = new BindException(
        new BindException(new BindException(new BindException(new BindException(
            new BindException(
                new BindException(new BindException(new BindException(bindingResult)))))))));
    ResponseEntity<ErrorResponse> actualHandleMethodArgumentNotValidResult = restExceptionHandler
        .handleMethodArgumentNotValid(new MethodArgumentNotValidException(
            new MethodParameter(
                new MethodParameter(
                    new MethodParameter(new MethodParameter(mock(MethodParameter.class))))),
            bindingResult1));
    assertTrue(actualHandleMethodArgumentNotValidResult.hasBody());
    assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
    assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
    ErrorResponse body = actualHandleMethodArgumentNotValidResult.getBody();
    assertEquals("MethodArgumentNotValidException", body.getException());
    List<io.cody.all_relationships.model.FieldError> fieldErrors = body.getFieldErrors();
    assertEquals(1, fieldErrors.size());
    assertEquals(400, body.getHttpStatus().intValue());
    io.cody.all_relationships.model.FieldError getResult = fieldErrors.get(0);
    assertNull(getResult.getErrorCode());
    assertEquals("Field", getResult.getField());
    verify(bindingResult).getFieldErrors();
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testHandleMethodArgumentNotValid3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.springframework.web.bind.MethodArgumentNotValidException.getBindingResult()" because "exception" is null
    //       at io.cody.all_relationships.config.RestExceptionHandler.handleMethodArgumentNotValid(RestExceptionHandler.java:34)
    //   In order to prevent handleMethodArgumentNotValid(MethodArgumentNotValidException)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   handleMethodArgumentNotValid(MethodArgumentNotValidException).
    //   See https://diff.blue/R013 to resolve this issue.

    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.getFieldErrors()).thenReturn(new ArrayList<>());
    new BindException(new BindException(new BindException(new BindException(new BindException(
        new BindException(
            new BindException(new BindException(new BindException(bindingResult)))))))));
    restExceptionHandler.handleMethodArgumentNotValid(null);
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testHandleMethodArgumentNotValid4() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   org.springframework.web.server.ResponseStatusException: 100 CONTINUE
    //       at org.springframework.validation.BindException.getFieldErrors(BindException.java:189)
    //       at org.springframework.validation.BindException.getFieldErrors(BindException.java:189)
    //       at org.springframework.validation.BindException.getFieldErrors(BindException.java:189)
    //       at org.springframework.validation.BindException.getFieldErrors(BindException.java:189)
    //       at org.springframework.validation.BindException.getFieldErrors(BindException.java:189)
    //       at org.springframework.validation.BindException.getFieldErrors(BindException.java:189)
    //       at org.springframework.validation.BindException.getFieldErrors(BindException.java:189)
    //       at org.springframework.validation.BindException.getFieldErrors(BindException.java:189)
    //       at org.springframework.validation.BindException.getFieldErrors(BindException.java:189)
    //       at io.cody.all_relationships.config.RestExceptionHandler.handleMethodArgumentNotValid(RestExceptionHandler.java:35)
    //   In order to prevent handleMethodArgumentNotValid(MethodArgumentNotValidException)
    //   from throwing ResponseStatusException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   handleMethodArgumentNotValid(MethodArgumentNotValidException).
    //   See https://diff.blue/R013 to resolve this issue.

    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.getFieldErrors()).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));
    BindException bindingResult1 = new BindException(
        new BindException(new BindException(new BindException(new BindException(
            new BindException(
                new BindException(new BindException(new BindException(bindingResult)))))))));
    restExceptionHandler.handleMethodArgumentNotValid(new MethodArgumentNotValidException(
        new MethodParameter(new MethodParameter(
            new MethodParameter(new MethodParameter(mock(MethodParameter.class))))),
        bindingResult1));
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException)}
   */
  @Test
  void testHandleMethodArgumentNotValid5() {
    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

    ArrayList<org.springframework.validation.FieldError> fieldErrorList = new ArrayList<>();
    fieldErrorList.add(
        new org.springframework.validation.FieldError("Object Name", "Field", "Default Message"));
    fieldErrorList.add(
        new org.springframework.validation.FieldError("Object Name", "Field", "Default Message"));
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.getFieldErrors()).thenReturn(fieldErrorList);
    BindException bindingResult1 = new BindException(
        new BindException(new BindException(new BindException(new BindException(
            new BindException(
                new BindException(new BindException(new BindException(bindingResult)))))))));
    ResponseEntity<ErrorResponse> actualHandleMethodArgumentNotValidResult = restExceptionHandler
        .handleMethodArgumentNotValid(new MethodArgumentNotValidException(
            new MethodParameter(
                new MethodParameter(
                    new MethodParameter(new MethodParameter(mock(MethodParameter.class))))),
            bindingResult1));
    assertTrue(actualHandleMethodArgumentNotValidResult.hasBody());
    assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
    assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
    ErrorResponse body = actualHandleMethodArgumentNotValidResult.getBody();
    assertEquals("MethodArgumentNotValidException", body.getException());
    List<io.cody.all_relationships.model.FieldError> fieldErrors = body.getFieldErrors();
    assertEquals(2, fieldErrors.size());
    assertEquals(400, body.getHttpStatus().intValue());
    io.cody.all_relationships.model.FieldError getResult = fieldErrors.get(1);
    assertEquals("Field", getResult.getField());
    io.cody.all_relationships.model.FieldError getResult1 = fieldErrors.get(0);
    assertEquals("Field", getResult1.getField());
    assertNull(getResult1.getErrorCode());
    assertNull(getResult.getErrorCode());
    verify(bindingResult).getFieldErrors();
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testHandleMethodArgumentNotValid6() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.springframework.validation.FieldError.getCode()" because "error" is null
    //       at io.cody.all_relationships.config.RestExceptionHandler.lambda$handleMethodArgumentNotValid$0(RestExceptionHandler.java:39)
    //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
    //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
    //       at io.cody.all_relationships.config.RestExceptionHandler.handleMethodArgumentNotValid(RestExceptionHandler.java:43)
    //   In order to prevent handleMethodArgumentNotValid(MethodArgumentNotValidException)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   handleMethodArgumentNotValid(MethodArgumentNotValidException).
    //   See https://diff.blue/R013 to resolve this issue.

    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

    ArrayList<FieldError> fieldErrorList = new ArrayList<>();
    fieldErrorList.add(null);
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.getFieldErrors()).thenReturn(fieldErrorList);
    BindException bindingResult1 = new BindException(
        new BindException(new BindException(new BindException(new BindException(
            new BindException(
                new BindException(new BindException(new BindException(bindingResult)))))))));
    restExceptionHandler.handleMethodArgumentNotValid(new MethodArgumentNotValidException(
        new MethodParameter(new MethodParameter(
            new MethodParameter(new MethodParameter(mock(MethodParameter.class))))),
        bindingResult1));
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleThrowable(Throwable)}
   */
  @Test
  void testHandleThrowable() {
    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
    ResponseEntity<ErrorResponse> actualHandleThrowableResult = restExceptionHandler.handleThrowable(
        new Throwable());
    assertTrue(actualHandleThrowableResult.hasBody());
    assertTrue(actualHandleThrowableResult.getHeaders().isEmpty());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualHandleThrowableResult.getStatusCode());
    ErrorResponse body = actualHandleThrowableResult.getBody();
    assertEquals("Throwable", body.getException());
    assertEquals(500, body.getHttpStatus().intValue());
  }

  /**
   * Method under test: {@link RestExceptionHandler#handleThrowable(Throwable)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testHandleThrowable2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.lang.Throwable.printStackTrace()" because "exception" is null
    //       at io.cody.all_relationships.config.RestExceptionHandler.handleThrowable(RestExceptionHandler.java:53)
    //   In order to prevent handleThrowable(Throwable)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   handleThrowable(Throwable).
    //   See https://diff.blue/R013 to resolve this issue.

    (new RestExceptionHandler()).handleThrowable(null);
  }
}

