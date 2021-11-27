package com.activeedge.interview.util.model.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.NonNull;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"message", "error"})
@Generated("jsonschema2pojo")
public class ResponseError implements Serializable {

    private final static long serialVersionUID = -4886351106258078331L;
    @NonNull
    @JsonProperty("message")
    private String message;
    @NonNull
    @JsonProperty("error")
    private String error;
    @NonNull
    @JsonProperty("errors")
    private Map<String, Object> errors = new HashMap<String, Object>();

    public ResponseError(ResponseErrorBuilder responseErrorBuilder) {
        this.message = responseErrorBuilder.message;
        this.error = responseErrorBuilder.error;
        this.errors = responseErrorBuilder.errors;
    }

    public static class ResponseErrorBuilder {
        @JsonProperty("message")
        private String message;
        @JsonProperty("error")
        private String error;
        @JsonProperty("errors")
        private Map<String, Object> errors;

        public ResponseErrorBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseErrorBuilder withError(String error) {
            this.error = error;
            return this;
        }

        public ResponseErrorBuilder withErrors(Map<String, Object> errors) {
            this.errors = errors;
            return this;
        }

        public ResponseError build() {
            return new ResponseError(this);
        }

    }
}
