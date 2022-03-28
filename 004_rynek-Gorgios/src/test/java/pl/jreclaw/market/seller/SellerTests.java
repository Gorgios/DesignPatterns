package pl.jreclaw.market.seller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jreclaw.market.model.Bank;
import pl.jreclaw.market.model.Offer;
import pl.jreclaw.market.model.Product;
import pl.jreclaw.market.model.Seller;
import pl.jreclaw.market.visitor.BigIncreaseVisitor;
import pl.jreclaw.market.visitor.MarketVisitor;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jreclaw.market.TestConstants.*;

public class SellerTests {

    Seller seller;
    Bank bank;

    @BeforeEach
    public void setUp() {
        seller = new Seller("Test");
        bank = Bank.getInstance();
    }

    @Test
    public void listOffer() {
        // arrange
        Offer offer = new Offer(new Product("test1"), PRICE, PRODUCTION_COST);

        // act
        seller.addOffer(offer);

        // assert
        assertThat(seller.getOffers())
                .isNotNull()
                .contains(offer)
                .hasSize(1);
    }

    @Test
    public void updateAfterInflation() {
        // arrange
        Offer offer1 = new Offer(new Product("test1"), PRICE, PRODUCTION_COST);
        Offer offer2 = new Offer(new Product("test2"), PRICE, PRODUCTION_COST);
        seller.addOffer(offer1);
        seller.addOffer(offer2);

        // act
        bank.setInflation(bank.getInflation() + 0.1);

        // assert
        List<Double> prices = seller.getOffers().stream().map(Offer::getPrice).collect(Collectors.toList());
        assertThat(prices).isEqualTo(List.of(PRICE + PRICE * 0.1, PRICE + PRICE * 0.1));
    }

    @Test
    public void acceptVisitor() {
        // arrange
        Offer offer1 = new Offer(new Product("test1"), PRICE, PRODUCTION_COST);
        Offer offer2 = new Offer(new Product("test2"), PRICE, PRODUCTION_COST);
        seller.addOffer(offer1);
        seller.addOffer(offer2);
        MarketVisitor visitor = new BigIncreaseVisitor();

        // act
        seller.acceptMarketChanges(visitor);

        // assert
        List<Double> prices = seller.getOffers().stream().map(Offer::getPrice).collect(Collectors.toList());
        assertThat(prices).isEqualTo(List.of(PRICE + PRICE * BIG_INCREASE_MUL, PRICE + PRICE * BIG_INCREASE_MUL));
    }

}
