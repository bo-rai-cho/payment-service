package com.mastercard.samples.services;


import com.mastercard.samples.clients.CardValidationClient;
import com.mastercard.samples.model.Card;
import com.mastercard.samples.model.Payment;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.atomic.AtomicLong;


public final class PaymentService {

    private CardValidationClient cardValidationClient;
    private static final AtomicLong PAYMENT_COUNT = new AtomicLong();

    public PaymentService(CardValidationClient cardValidationClient) {
        this.cardValidationClient = cardValidationClient;
    }

    public ResponseEntity post(Payment payment) {

        Card card = payment.getCard();

        if (cardValidationClient.isValid(card)) {
            payment.setSuccess(true);
        } else {
            payment.setSuccess(false);
        }

        payment.setId(PAYMENT_COUNT.getAndIncrement());
        return ResponseEntity.ok(payment);
    }
}
