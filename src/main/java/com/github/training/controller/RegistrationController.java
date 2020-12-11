package com.github.training.controller;

import com.github.training.controller.request.RegisterAccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegistrationController {
    @PostMapping("/registration")
    public void registration(@RequestBody RegisterAccountRequest registerAccountRequest){
        log.info("Registration request test arrived: {}", registerAccountRequest);
    }
}
