package pl.jreclaw.market.interest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.jreclaw.market.model.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.mockito.Mockito.when;
import static pl.jreclaw.market.TestConstants.PRICE;
import static pl.jreclaw.market.TestConstants.PRODUCTION_COST;

public class InterestTests {

    Client client;

    @BeforeEach
    public void setUp() {
        client = new Client("Test");
    }

    @Test
    public void updateAfterPriceChange() {
        // arrange
        Offer offer = new Offer(new Product("test"), PRICE, PRODUCTION_COST);
        Interest interest = new Interest(offer, client);
        interest.setInterest(0.5);
        offer.addFollower(interest);
        double currentInterest = interest.getInterest();

        // act
        offer.increasePriceByPercent(0.1);

        // assert
        assertThat(interest.getInterest())
                .isLessThan(currentInterest);
    }

    @Test
    public void buyIfInterestIncrease() {
        // arrange
        Product p1 = new Product("test");
        Seller seller = new Seller("Test");
        Offer offer = new Offer(p1, PRICE, PRODUCTION_COST);
        seller.addOffer(offer);
        Interest interest = new Interest(offer, client);
        offer.addFollower(interest);
        interest.setInterest(0.75);

        // act
        offer.increasePriceByPercent(-0.1);

        // assert
        assertThat(client.getBoughtProducts())
                .hasSize(1)
                .contains(p1);
    }
}
