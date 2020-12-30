package com.github.training.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String houseNumber;
    private String floor;
    private String door;
}
