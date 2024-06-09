package com.lenhatthanh.blog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenhatthanh.blog.core.controller.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.Date;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ExceptionResponse exceptionResponse = createExceptionResponse(
                request.getRequestURI(),
                new Date()
        );

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(exceptionResponse));
    }

    private ExceptionResponse createExceptionResponse(String uri, Date date) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode("UNAUTHORIZED-ERROR-0001");
        exceptionResponse.setErrorMessage("Unauthorized: Authentication token was either missing or invalid.");
        exceptionResponse.setRequestedURI(uri);
        exceptionResponse.setTime(date);

        return exceptionResponse;
    }
}
