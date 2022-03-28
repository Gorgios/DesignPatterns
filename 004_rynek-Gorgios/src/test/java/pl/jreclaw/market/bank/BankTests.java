package pl.jreclaw.market.bank;

import org.junit.jupiter.api.Test;
import pl.jreclaw.market.model.*;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jreclaw.market.TestConstants.PRICE;
import static pl.jreclaw.market.TestConstants.PRODUCTION_COST;

public class BankTests {

    @Test
    public void updateInflationAfterTransaction() {
        // arrange
        Bank bank = Bank.getInstance();
        Client client = new Client("Test");
        Seller seller = new Seller("Test");
        Offer offer = new Offer(new Product("test"), PRICE, PRODUCTION_COST);
        Offer offer2 = new Offer(new Product("test2"), PRICE * 10, PRODUCTION_COST);
        seller.addOffer(offer);
        seller.addOffer(offer2);
        double inflation = bank.getInflation();

        // act
        offer.buy(client);
        offer2.buy(client);

        // assert
        assertThat(bank.getInflation())
                .isGreaterThan(inflation);
    }
}
