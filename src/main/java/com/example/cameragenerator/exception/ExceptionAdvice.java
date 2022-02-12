package com.example.cameragenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.net.ConnectException;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({ConstraintViolationException.class})
    public ApiError handleConstraintViolationException(ConstraintViolationException exception) {
        return wrapValidException(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected ApiError handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return wrapValidException("Значение параметра " + ex.getName() + " должно быть больше или равно 1", HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler({ConnectException.class})
    public ApiError handleConnectException(ConnectException ex) {
        return wrapSystemException("Сервис временно недоступен. Повторите попытку позже", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ApiError wrapBusinessException(Throwable throwable, HttpStatus status) {
        return ApiError.builder()
                .message(throwable.getMessage())
                .status(status)
                .type(ApiErrorType.BUSINESS)
                .build();
    }

    private ApiError wrapValidException(String message, HttpStatus status) {
        return ApiError.builder()
                .message(message)
                .status(status)
                .type(ApiErrorType.VALIDATION)
                .build();
    }

    private ApiError wrapSystemException(String message, HttpStatus status) {
        return ApiError.builder()
                .message(message)
                .status(status)
                .type(ApiErrorType.SYSTEM)
                .build();
    }

}
