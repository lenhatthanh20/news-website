package com.lenhatthanh.blog.core.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExceptionResponse {
    private String errorCode;
    private String errorMessage;
    private String requestedURI;
    private Date time;
}
