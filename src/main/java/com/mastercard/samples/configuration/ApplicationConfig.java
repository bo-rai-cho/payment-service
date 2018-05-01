package com.mastercard.samples.configuration;

import com.mastercard.samples.clients.CardValidationClient;
import com.mastercard.samples.services.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
public class ApplicationConfig {

    @Bean
    public PaymentService paymentService(CardValidationClient cardValidationClient) {
        return new PaymentService(cardValidationClient);
    }

    @Bean
    public CardValidationClient cardValidationClient(RestOperations restOperations) {
        return new CardValidationClient(restOperations);
    }
}
