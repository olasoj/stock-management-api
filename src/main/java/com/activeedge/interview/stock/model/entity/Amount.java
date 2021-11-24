package com.activeedge.interview.stock.model.entity;

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

    public Amount(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
