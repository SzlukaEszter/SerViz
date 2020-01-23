package com.codecool.servicecheckout.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    private Long id;
    private List<LineItem> lineItems;
    private Long sum;
    private Long userId;

    private String stringifyLines(){
       return  lineItems.stream().map(LineItem::toString).collect(Collectors.joining("\n"));
    }

    public String toString(){
       return stringifyLines()
               + "\n\n"
               + "Total: " + sum + "HUF";

    }


}
