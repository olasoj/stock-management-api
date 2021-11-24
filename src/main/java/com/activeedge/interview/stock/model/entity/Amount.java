package com.activeedge.interview.stock.model.entity;

import com.activeedge.interview.stock.model.request.create.CreateAmountRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Amount implements Serializable {
    private BigDecimal currentPrice;

    public Amount(CreateAmountRequest createAmountRequest) {
        this.currentPrice = createAmountRequest.getCurrentPrice();
    }
}
