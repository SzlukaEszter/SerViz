package com.codecool.servicecart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart", cascade = CascadeType.ALL)
    @Builder.Default
    private List<LineItem> lineItems = new ArrayList<LineItem>();

    @Transient
    private Long sum = 0L;

    private Long userId;

    public Long getSum() {
        return getLineItems().stream()
                .mapToLong(LineItem::getLineItemSumPrice)
                .sum();
    }
}
