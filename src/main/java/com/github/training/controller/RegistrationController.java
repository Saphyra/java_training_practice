package com.github.training.controller;

import com.github.training.controller.request.RegisterAccountRequest;
import com.github.training.validation.RegisterAccountRequestValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RegistrationController {
    private final RegisterAccountRequestValidation registerAccountRequestValidation;
    @PostMapping("/registration")
    public void registration(@RequestBody RegisterAccountRequest registerAccountRequest){
        log.info("Registration request test arrived: {}", registerAccountRequest);
        boolean regAccVal = registerAccountRequestValidation.registerAccountValidation(registerAccountRequest);
    }

}
