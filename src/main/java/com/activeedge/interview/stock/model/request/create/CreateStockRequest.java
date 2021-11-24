package com.activeedge.interview.stock.model.request.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateStockRequest implements Serializable {
    @JsonProperty("name")
    @NotEmpty(message = "Enter stock name")
    private String name;

    @JsonProperty("amount")
    @NotNull(message = "Enter Amount")
    @Valid
    private CreateAmountRequest amount;
}
