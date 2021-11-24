package com.activeedge.interview.stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "stock", indexes = {@Index(name = "create_date_index", columnList = "create_date")})
@NoArgsConstructor
@Getter
@Setter
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id", updatable = false, nullable = false)
    private Long stockId;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(name = "documentFormat", column = @Column(name = "valid_id_document_format", nullable = false))
    @AttributeOverride(name = "documentLink", column = @Column(name = "valid_id_link", nullable = false))
    @AttributeOverride(name = "documentName", column = @Column(name = "valid_id_name", nullable = false))
    @Column(name = "current_price", nullable = false)
    private Amount currentPrice;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "last_update")
    private LocalDateTime modifiedDate;

    public Stock(String name, Amount currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;

    }

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = LocalDateTime.now();
    }
}
