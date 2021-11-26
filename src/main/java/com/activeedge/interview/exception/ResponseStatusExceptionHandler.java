package com.activeedge.interview.exception;

import com.activeedge.interview.util.model.response.ResponseModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
public class ResponseStatusExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseStatusExceptionHandler.class);

    @ExceptionHandler(ResponseStatusException.class)
    public void handleResponseStatusException(HttpServletRequest request, HttpServletResponse response, ResponseStatusException ex) {
        LOGGER.error(ex.getMessage(), ex);
        ResponseModel.responseError(request, response, ex.getReason(), ex.getStatus());
    }
}
