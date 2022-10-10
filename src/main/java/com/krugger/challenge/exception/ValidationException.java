package com.krugger.challenge.exception;

import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {

    private HttpStatus httpStatus;

    public ValidationException(String message) {
        super(message);
        this.httpStatus = HttpStatus.PRECONDITION_FAILED;
    }

    public ValidationException(Exception e) {
        super(e);
        this.httpStatus = HttpStatus.EXPECTATION_FAILED;
    }

    public ValidationException(String exString, Set<ConstraintViolation<Object>> constraintViolations) {
        super(createMessage(exString, constraintViolations));
        this.httpStatus = HttpStatus.PRECONDITION_FAILED;
    }

    private static String createMessage(String exString, Set<ConstraintViolation<Object>> constraintViolations) {
        if (constraintViolations == null || constraintViolations.isEmpty())
            return exString == null ? "":exString;

        return String.format("Datos incorrectos: %s",
                constraintViolations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(", "))
        );
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
