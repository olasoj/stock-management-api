package com.activeedge.interview.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorAttributes.class);
    public final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logResponseStatusException(ex);
        return super.resolveException(request, response, handler, ex);
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        var errorAttributes = super.getErrorAttributes(webRequest, options);
        HttpStatus status = HttpStatus.valueOf((Integer) errorAttributes.get("status"));
        errorAttributes.put("timestamp", timestamp);
        errorAttributes.put("response_state", status.is2xxSuccessful());
        return errorAttributes;
    }

    private void logResponseStatusException(Exception ex) {
        if (ex.getClass().equals(ResponseStatusException.class)) LOGGER.warn(ex.getMessage(), ex);
    }
}
