package com.github.training.controller;

import com.github.training.controller.request.RegisterAccountRequest;
import com.github.training.database.Account;
import com.github.training.database.Currency;
import com.github.training.database.ShippingInformation;
import com.github.training.validation.RegisterAccountRequestValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RegistrationController {
    private final RegisterAccountRequestValidation registerAccountRequestValidation;

    @PostMapping("/registration")
    public void registration(@RequestBody RegisterAccountRequest registerAccountRequest){
        log.info("Registration request test arrived: {}", registerAccountRequest);
        boolean regAccVal = registerAccountRequestValidation.registerAccountValidation(registerAccountRequest);
        if (regAccVal == false){
            log.error("Nem megfelelő adatok");
            throw new IllegalArgumentException("Nem megfelelő adatok");
        }
        Account account = new Account();

        account.setUsername(registerAccountRequest.getUsername());
        account.setPassword(registerAccountRequest.getPassword());
        account.setEmail(registerAccountRequest.getEmail());
        account.setCurrency(Currency.valueOf(registerAccountRequest.getCurrency()));

        ShippingInformation shippingInformation = new ShippingInformation();

        shippingInformation.setName(registerAccountRequest.getName());
        shippingInformation.setCountry(registerAccountRequest.getAddress().getCountry());
        shippingInformation.setZipCode(registerAccountRequest.getAddress().getZipCode());
        shippingInformation.setCity(registerAccountRequest.getAddress().getCity());
        shippingInformation.setStreet(registerAccountRequest.getAddress().getStreet());
        shippingInformation.setHouseNumber(registerAccountRequest.getAddress().getHouseNumber());
        shippingInformation.setFloor(registerAccountRequest.getAddress().getFloor());
        shippingInformation.setDoor(registerAccountRequest.getAddress().getDoor());
        shippingInformation.setPhoneNumber(registerAccountRequest.getPhoneNumber());
        shippingInformation.setBirthDate(LocalDate.parse(registerAccountRequest.getBirthDate()));
    }
}
