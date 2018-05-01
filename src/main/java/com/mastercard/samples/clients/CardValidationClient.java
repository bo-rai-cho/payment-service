package com.mastercard.samples.clients;

import com.mastercard.samples.model.Card;
import org.springframework.web.client.RestOperations;


public class CardValidationClient {

    private RestOperations restOperations;

    public CardValidationClient(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public Boolean isValid(Card card) {

        return restOperations.getForEntity("http://localhost:8090/card/" + card.getCardNumber(), Card.class)
                .getBody()
                .getValid();
    }
}
