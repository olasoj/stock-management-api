package com.activeedge.interview.exception;

import com.activeedge.interview.util.model.response.ResponseModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@AllArgsConstructor
public class HttpMethodNotAssignToUrlErrHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMethodNotAssignToUrlErrHandler.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public void handleNoHandlerFoundException(HttpServletRequest request, HttpServletResponse response, NoHandlerFoundException ex) {
        var message = String.format("Could not find the %s method for URL: %s", ex.getHttpMethod(), ex.getRequestURL());
        LOGGER.error(ex.getMessage(), ex);
        ResponseModel.responseError(request, response, message, HttpStatus.NOT_FOUND);
    }
}