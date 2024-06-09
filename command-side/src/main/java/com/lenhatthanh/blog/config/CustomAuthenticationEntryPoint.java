package com.lenhatthanh.blog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenhatthanh.blog.core.controller.ExceptionResponse;
import com.lenhatthanh.blog.shared.Messages;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@AllArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private Messages messages;

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
        String code = "AUTHENTICATION-ERROR-0001";
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(code);
        exceptionResponse.setErrorMessage(messages.getMessage(code));
        exceptionResponse.setRequestedURI(uri);
        exceptionResponse.setTime(date);

        return exceptionResponse;
    }
}
