package com.github.training.database;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class ShippingInformation {
    @Id
    private UUID userId;
    private String name;
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String houseNumber;
    private String floor;
    private String door;
    private String phoneNumber;
    private String birthDate;
}
