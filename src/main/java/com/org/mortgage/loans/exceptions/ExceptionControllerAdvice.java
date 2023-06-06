package com.org.mortgage.loans.exceptions;

import com.org.mortgage.loans.model.ApiErrorResponse;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(Exception e) {
        log.error("Caught exception", e);
        return respond(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            IllegalArgumentException.class,
            ValidationException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ApiErrorResponse> badRequestExceptionHandler(Exception e) {
        log.error("Caught exception", e);
        return respond(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiErrorResponse> respond(Exception e, HttpStatus httpStatus) {
        ApiErrorResponse error = new ApiErrorResponse();
        error.setErrorCode(httpStatus.value());
        error.setTimeStamp(DateTime.now());
        error.setMessage(ExceptionUtils.getMessage(e));
        return new ResponseEntity<>(error, httpStatus);
    }

}
