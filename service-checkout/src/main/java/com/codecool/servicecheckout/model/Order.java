package com.codecool.servicecheckout.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private DeliveryAddress deliveryAddress;
    private Cart cart;
   private Long userId;

   public String toString(){
       return "Delivery Address:\n\n" + deliveryAddress.toString() + "\n\nOrder:\n\n"
               + cart.toString() + "\n";
   }
}
