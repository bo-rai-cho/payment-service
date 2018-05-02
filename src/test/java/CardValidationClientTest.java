import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

import com.mastercard.samples.clients.CardValidationClient;
import com.mastercard.samples.configuration.ApplicationConfig;
import com.mastercard.samples.configuration.JsonMapperConfig;
import com.mastercard.samples.configuration.RestConfig;
import com.mastercard.samples.model.Card;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;


import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, RestConfig.class, JsonMapperConfig.class})
public class CardValidationClientTest {

    private static final Card TEST_CARD = new Card("Batman", 5455031500000173L, null);

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("CardValidationService", "localhost", 8091,this);

    @Autowired
    private CardValidationClient cardValidationClient;

    @Autowired
    private RestOperations restOperations;

    @Pact(consumer = "PaymentService", provider = "CardValidationService")
    public RequestResponsePact okResponse(PactDslWithProvider builder) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("normal card")
                .uponReceiving("card validation request")
                    .path("/card/" + TEST_CARD.getCardNumber())
                    .method("GET")
                .willRespondWith()
                    .headers(headers)
                    .status(200)
                    .body(new Card(TEST_CARD.getOwner(), TEST_CARD.getCardNumber(), true).toJsonString())
                .toPact();
    }

    @Test
    @PactVerification
    public void testNormalCard() {

        ResponseEntity<Card> re = restOperations.getForEntity(mockProvider.getUrl() + "/card/get/" + TEST_CARD.getCardNumber(), Card.class);

        Card expected = new Card(TEST_CARD.getOwner(), TEST_CARD.getCardNumber(), true);
        Card received = re.getBody();

        Assert.assertEquals(expected, received);

    }

}
