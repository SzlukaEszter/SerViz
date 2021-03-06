package com.codecool.servicecheckout.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddress {

    @Id
    @GeneratedValue
    private Long id;

    private String country;

    private String city;

    private String address;

    private String postalcode;

    private Long userId;

    private boolean active = false;

    public String toString(){
        return country + "\n "
                +postalcode + " " + city + "\n"
                +address;
    }


}
