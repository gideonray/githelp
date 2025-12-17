package com.branchapp.githelp.api;

import com.branchapp.githelp.github.GithubRateLimitException;
import com.branchapp.githelp.github.GithubUpstreamException;
import com.branchapp.githelp.github.GithubUserNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleBadRequest(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList();
    }

    @ExceptionHandler(GithubUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(GithubUserNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(GithubRateLimitException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String rateLimited(GithubRateLimitException e) {
        return e.getMessage();
    }

    @ExceptionHandler(GithubUpstreamException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String upstream(GithubUpstreamException e) {
        return e.getMessage();
    }

}
