package com.activeedge.interview.stock.model.request.update;

import com.activeedge.interview.stock.model.request.create.CreateAmountRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateStockRequest implements Serializable {
    @JsonProperty("name")
    private String name;

    @JsonProperty("amount")
    @Valid
    private CreateAmountRequest amount;
}
