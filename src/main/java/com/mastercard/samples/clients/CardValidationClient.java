package com.mastercard.samples.clients;

import com.mastercard.samples.model.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;


public class CardValidationClient {

    private RestOperations restOperations;

    public CardValidationClient(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public Boolean isValid(Card card) {

        ResponseEntity<Card> re = restOperations.getForEntity("http://docker.for.mac.localhost:8090/card/get/" + card.getCard(), Card.class);

        return re.getBody().getValid();
    }
}
