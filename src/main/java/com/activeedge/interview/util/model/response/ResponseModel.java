package com.activeedge.interview.util.model.response;

import com.activeedge.interview.util.model.response.model.Response;
import com.activeedge.interview.util.model.response.model.ResponseError;
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
import java.util.Map;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseModel.class);
    private static final String RESPONSE_MESSAGE = "Failed to process response message";

    public static void responseError(HttpServletRequest request, HttpServletResponse response, String message, HttpStatus status) {
        var responseError = new ResponseError.ResponseErrorBuilder()
                .withError(status.getReasonPhrase())
                .withMessage(message)
                .build();

        responseWriter(request, response, status, responseError);
    }

    public static void responseValidationError(HttpServletRequest request, HttpServletResponse response,
                                               Map<String, Object> errors, String message, HttpStatus status) {
        var responseError = new ResponseError.ResponseErrorBuilder()
                .withError(status.getReasonPhrase())
                .withMessage(message)
                .withErrors(errors)
                .build();

        responseWriter(request, response, status, responseError);
    }

    public static <T> void responseWriter(HttpServletRequest request, HttpServletResponse response, HttpStatus status, T responseMessage) {
        try {
            response.setStatus(status.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=" + StandardCharsets.UTF_8.name());
            getJacksonObjectMapper().writeValue(response.getWriter(), getResponseModel(request, status, responseMessage));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE);
        }
    }

    private static <T> Response<T> getResponseModel(HttpServletRequest request, HttpStatus httpStatus, T data) {
        return new Response.ResponseBuilder<T>()
                .withPath(request.getServletPath())
                .withResponseState(httpStatus.is2xxSuccessful())
                .withTimestamp(getTimeStamp())
                .withStatus(httpStatus.value())
                .withData(data)
                .build();
    }

    private static ObjectMapper getJacksonObjectMapper() {
        return new ObjectMapper();
    }

    private static String getTimeStamp() {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(dateTimeFormatter);
    }
}
