package com.activeedge.interview.exception.file;

import com.activeedge.interview.util.model.response.ResponseModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
public class FileExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileExceptionHandler.class);

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void handleMaxSizeException(HttpServletRequest request, HttpServletResponse response, MaxUploadSizeExceededException ex) {
        LOGGER.error(ex.getMessage(), ex);
        ResponseModel.responseError(request, response, "Exceeded Server limit of 1MB", HttpStatus.EXPECTATION_FAILED);
    }
}