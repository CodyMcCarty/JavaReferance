package io.cody.all_relationships.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ErrorResponseTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ErrorResponse}
   *   <li>{@link ErrorResponse#setException(String)}
   *   <li>{@link ErrorResponse#setFieldErrors(List)}
   *   <li>{@link ErrorResponse#setHttpStatus(Integer)}
   *   <li>{@link ErrorResponse#setMessage(String)}
   *   <li>{@link ErrorResponse#getException()}
   *   <li>{@link ErrorResponse#getFieldErrors()}
   *   <li>{@link ErrorResponse#getHttpStatus()}
   *   <li>{@link ErrorResponse#getMessage()}
   * </ul>
   */
  @Test
  void testConstructor() {
    ErrorResponse actualErrorResponse = new ErrorResponse();
    actualErrorResponse.setException("Exception");
    ArrayList<FieldError> fieldErrorList = new ArrayList<>();
    actualErrorResponse.setFieldErrors(fieldErrorList);
    actualErrorResponse.setHttpStatus(1);
    actualErrorResponse.setMessage("Not all who wander are lost");
    assertEquals("Exception", actualErrorResponse.getException());
    assertSame(fieldErrorList, actualErrorResponse.getFieldErrors());
    assertEquals(1, actualErrorResponse.getHttpStatus().intValue());
    assertEquals("Not all who wander are lost", actualErrorResponse.getMessage());
  }
}

