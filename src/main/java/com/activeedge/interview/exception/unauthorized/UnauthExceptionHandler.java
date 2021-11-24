package com.activeedge.interview.exception.unauthorized;

import com.activeedge.interview.util.model.response.ResponseModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
public class UnauthExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnauthExceptionHandler.class);

    @ExceptionHandler(value = {AuthenticationException.class})
    public void handleAuthenticationException(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        LOGGER.error(e.getMessage(), e);
        ResponseModel.responseError(request, response, e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public void handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        LOGGER.error(e.getMessage(), e);
        ResponseModel.responseError(request, response, "You aren't permitted to access this resource", HttpStatus.FORBIDDEN);
    }

}
