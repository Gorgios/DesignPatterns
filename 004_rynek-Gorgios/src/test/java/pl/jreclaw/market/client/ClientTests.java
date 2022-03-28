package pl.jreclaw.market.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.jreclaw.market.model.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jreclaw.market.TestConstants.PRICE;
import static pl.jreclaw.market.TestConstants.PRODUCTION_COST;

public class ClientTests {
    private static final double INTEREST = 0.5;
    Client client;
    Bank bank;
    @Mock
    Seller seller;

    @BeforeEach
    public void setUp() {
        client = new Client("Test");
        bank = Bank.getInstance();
    }

    @Test
    public void followOffer() {
        // arrange
        Offer offer = new Offer(new Product("test1"), PRICE, PRODUCTION_COST);
        offer.setSeller(seller);

        // act
        client.followOffer(offer);

        // assert
        assertThat(client.getInterests())
                .isNotNull()
                .hasSize(1);
    }

    @Test
    public void updateAfterInflation() {
        // arrange
        Offer offer1 = new Offer(new Product("test1"), PRICE, PRODUCTION_COST);
        offer1.setSeller(seller);
        Offer offer2 = new Offer(new Product("test2"), PRICE, PRODUCTION_COST);
        offer2.setSeller(seller);
        client.followOffer(offer1);
        client.followOffer(offer2);
        client.getInterests().forEach(e -> e.setInterest(INTEREST));

        // act
        bank.setInflation(bank.getInflation() + 0.1);

        // assert
        List<Double> prices = client.getInterests().stream().map(Interest::getInterest).collect(Collectors.toList());
        assertThat(prices)
                .doesNotContain(INTEREST, INTEREST)
                .allMatch(e -> e < INTEREST);
    }
}
