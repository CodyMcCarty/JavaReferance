package io.cody.all_relationships.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FieldErrorTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link FieldError}
   *   <li>{@link FieldError#setErrorCode(String)}
   *   <li>{@link FieldError#setField(String)}
   *   <li>{@link FieldError#getErrorCode()}
   *   <li>{@link FieldError#getField()}
   * </ul>
   */
  @Test
  void testConstructor() {
    FieldError actualFieldError = new FieldError();
    actualFieldError.setErrorCode("An error occurred");
    actualFieldError.setField("Field");
    assertEquals("An error occurred", actualFieldError.getErrorCode());
    assertEquals("Field", actualFieldError.getField());
  }
}

