package com.mastercard.samples.controllers;

import com.mastercard.samples.model.Payment;
import com.mastercard.samples.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    private PaymentService statesService;

    public PaymentController(PaymentService statesService) {
        this.statesService = statesService;
    }

    @RequestMapping(method = POST)
    public ResponseEntity post(@RequestBody Payment payment) {
        return statesService.post(payment);
    }
}
