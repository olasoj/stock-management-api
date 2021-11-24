package com.activeedge.interview.exception.filter;

import com.activeedge.interview.util.model.response.ResponseModel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@AllArgsConstructor
public class Err5XXFilter implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {

        var res = ((ServletServerHttpResponse) response).getServletResponse();
        var req = ((ServletServerHttpRequest) request).getServletRequest();
        if (handleErr5XXErr(req, res)) return null;
        return body;
    }

    private boolean handleErr5XXErr(HttpServletRequest req, HttpServletResponse res) {
        int status = res.getStatus();
        var httpStatus = HttpStatus.valueOf(status);
        if (status == 500) {
            ResponseModel.responseError(req, res, "An internal server occurred", httpStatus);
            return true;
        }
        return false;
    }
}
