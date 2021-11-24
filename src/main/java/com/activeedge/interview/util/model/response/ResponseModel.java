package com.activeedge.interview.util.model.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseModel.class);
    private static final String RESPONSE_MESSAGE = "Failed to process response message";

    public static void responseError(HttpServletRequest request, HttpServletResponse response, String message, HttpStatus status) {
        var responseMessage = getErrorResponseModel(request, status, message);
        responseConfig(request, response, status, responseMessage);
    }

    public static void responseValidationError(HttpServletRequest request, HttpServletResponse response,
                                               Map<Object, Object> errors, String message, HttpStatus status) {
        var responseMessage = getErrorResponseModel(request, status, message);
        responseMessage.put("errors", errors);
        responseConfig(request, response, status, responseMessage);
    }

    public static void responseSuccess(HttpServletRequest request, HttpServletResponse response,
                                       HttpStatus status, String messageName, Object message) {
        responseConfig(request, response, status, getGenericResponseModel(request, status, message, messageName));
    }

    private static Map<String, Object> getErrorResponseModel(HttpServletRequest request, HttpStatus status, Object message) {
        var responseMessage = getGenericResponseModel(request, status, message, "message");
        responseMessage.put("status", status.value());
        responseMessage.put("error", status.getReasonPhrase());
        return responseMessage;
    }

    private static Map<String, Object> getGenericResponseModel(HttpServletRequest request, HttpStatus httpStatus, Object message, String messageName) {
        var successful = (httpStatus.is2xxSuccessful());
        var responseMessage = new HashMap<String, Object>();
        responseMessage.put("timestamp", getTimeStamp());
        responseMessage.put(messageName, message);
        responseMessage.put("response_state", successful);
        responseMessage.put("path", request.getServletPath());
        return responseMessage;
    }

    private static void responseConfig(HttpServletRequest httpServletRequest, HttpServletResponse response, HttpStatus status, Map<String, Object> responseMessage) {
        try {
            response.setStatus(status.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=" + StandardCharsets.UTF_8.name());
            getJacksonObjectMapper().writeValue(response.getWriter(), responseMessage);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE);
        }
    }

    private static ObjectMapper getJacksonObjectMapper() {
        return new ObjectMapper();
    }

    private static String getTimeStamp() {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(dateTimeFormatter);
    }
}
