package com.activeedge.interview.util.model.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"path", "timestamp", "response_state", "status", "data"})
@Generated("jsonschema2pojo")
public class Response<T> implements Serializable {
    private final static long serialVersionUID = -4886351106258078331L;
    @NonNull
    @JsonProperty("path")
    private String path;

    @NonNull
    @JsonProperty("response_state")
    private boolean responseState;

    @NonNull
    @JsonProperty("timestamp")
    private String timestamp;

    @NonNull
    @JsonProperty("status")
    private Integer status;

    @NonNull
    @JsonProperty("data")
    private T data;

    public Response(ResponseBuilder<T> responseBuilder) {
        this.path = responseBuilder.path;
        this.responseState = responseBuilder.responseState;
        this.timestamp = responseBuilder.timestamp;
        this.status = responseBuilder.status;
        this.data = responseBuilder.data;
    }

    public static class ResponseBuilder<BT> {
        @JsonProperty("path")
        private String path;
        @JsonProperty("response_state")
        private boolean responseState;
        @JsonProperty("timestamp")
        private String timestamp;
        @JsonProperty("status")
        private Integer status;
        @JsonProperty("data")
        private BT data;

        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public ResponseBuilder<BT> withPath(String path) {
            this.path = path;
            return this;
        }

        public ResponseBuilder<BT> withResponseState(boolean responseState) {
            this.responseState = responseState;
            return this;
        }

        public ResponseBuilder<BT> withTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ResponseBuilder<BT> withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder<BT> withData(BT data) {
            this.data = data;
            return this;
        }

        public Response<BT> build() {
            return new Response<BT>(this);
        }

    }
}