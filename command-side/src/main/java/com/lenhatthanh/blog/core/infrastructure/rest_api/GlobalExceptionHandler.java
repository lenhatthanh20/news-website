package com.lenhatthanh.blog.core.infrastructure.rest_api;

import com.lenhatthanh.blog.core.application.ApplicationException;
import com.lenhatthanh.blog.core.domain.DomainException;
import com.lenhatthanh.blog.modules.article.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.article.domain.exception.InvalidArticleContentException;
import com.lenhatthanh.blog.modules.article.domain.exception.InvalidArticleTitleException;
import com.lenhatthanh.blog.modules.article.domain.exception.InvalidSlugException;
import com.lenhatthanh.blog.modules.user.domain.exception.*;
import com.lenhatthanh.blog.shared.Messages;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Messages messages;

    @ExceptionHandler({
            InvalidArticleTitleException.class,
            InvalidArticleContentException.class,
            InvalidSlugException.class,
            InvalidUserNameException.class,
            EmailNotEmptyException.class,
            InvalidEmailException.class,
            RoleAlreadyExistException.class,
            RoleNotFoundException.class,
            InvalidRoleNameException.class,
            InvalidRoleDescriptionException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleDomainException(DomainException exception, final HttpServletRequest request) {
        return createExceptionResponse(exception.getCode(), messages.getMessage(exception.getCode()), request.getRequestURI(), new Date());
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleApplicationException(ApplicationException exception, final HttpServletRequest request) {
        return createExceptionResponse(exception.getCode(), messages.getMessage(exception.getCode()), request.getRequestURI(), new Date());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ExceptionResponse handleRuntimeExceptions(RuntimeException exception, final HttpServletRequest request) {
        String code = "BAD-REQUEST-ERROR-0001";

        return createExceptionResponse(code, messages.getMessage(code), request.getRequestURI(), new Date());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleUnwantedExceptions(Exception exception, final HttpServletRequest request) {
        String code = "UNKNOWN-REQUEST-ERROR-0001";

        return createExceptionResponse(code, messages.getMessage(code), request.getRequestURI(), new Date());
    }

    private ExceptionResponse createExceptionResponse(String code, String message, String uri, Date date) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(code);
        exceptionResponse.setErrorMessage(message);
        exceptionResponse.setRequestedURI(uri);
        exceptionResponse.setTime(date);

        return exceptionResponse;
    }
}
