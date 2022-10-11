package com.krugger.challenge.exception;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ValidationExceptionTest {

    private String exString;

    @Mock
    private Set<ConstraintViolation<?>> constraintViolations;

    @Mock
    ValidationException validationException;

    @Test
    public void shouldReturnExceptionString() {
        exString = "Exception String";
        validationException = new ValidationException(exString, null);
        Assertions.assertThat(validationException.getMessage().equals(exString));
    }

    @Test
    public void shouldReturnEmptyString() {
        validationException = new ValidationException(exString, null);
        Assertions.assertThat(validationException.getMessage().isEmpty());
    }

    @Test
    public void shouldReturnNonEmptyString() {
        validationException = new ValidationException(exString, constraintViolations);
        Assertions.assertThat(validationException.getMessage()).isNotEmpty();
    }

    @Test
    public void shouldReturnException() {
        constraintViolations.add(null);
        validationException = new ValidationException(exString, constraintViolations);
        Assertions.assertThat(validationException.getMessage()).isNotEmpty();
    }

    @Test
    public void shouldReturnCustomValidationException() {
        validationException = new ValidationException("Not Found");
        Assertions.assertThat(validationException.getMessage().equals("Not Found"));
    }
}
