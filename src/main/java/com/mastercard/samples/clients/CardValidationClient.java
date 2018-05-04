package com.mastercard.samples.clients;

import com.mastercard.samples.model.Card;
import org.springframework.web.client.RestOperations;


public class CardValidationClient {

    private RestOperations restOperations;

    public static String EXTERNAL_URL = "http://localhost:8090/card/get/";

    public CardValidationClient(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public Boolean isValid(Card card) {

        return restOperations.getForEntity(EXTERNAL_URL + card.getCardNumber(), Card.class).hasBody();
    }
}
