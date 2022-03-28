package pl.jreclaw.market.visitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.jreclaw.market.model.Offer;
import pl.jreclaw.market.model.Product;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jreclaw.market.TestConstants.*;

public class VisitorTests {

    @Mock
    Product product;
    Offer offer;

    @BeforeEach
    public void setUp() {
        offer = new Offer(product, PRICE, PRODUCTION_COST);
    }


    @Test
    public void testBigDecreaseVisitOffer() {
        // arrange
        MarketVisitor visitor = new BigDecreaseVisitor();

        // act
        visitor.visit(offer);

        // assert
        assertThat(offer.getPrice())
                .isEqualTo(PRICE + PRICE * BIG_DECREASE_MUL);
    }

    @Test
    public void testLowDecreaseVisitOffer() {
        // arrange
        MarketVisitor visitor = new LowDecreaseVisitor();

        // act
        visitor.visit(offer);

        // assert
        assertThat(offer.getPrice())
                .isEqualTo(PRICE + PRICE * LOW_DECREASE_MUL);
    }

    @Test
    public void testLowIncreaseVisitOffer() {
        // arrange
        MarketVisitor visitor = new LowIncreaseVisitor();

        // act
        visitor.visit(offer);

        // assert
        assertThat(offer.getPrice())
                .isEqualTo(PRICE + PRICE * LOW_INCREASE_MUL);
    }

    @Test
    public void testBigIncreaseVisitOffer() {
        // arrange
        MarketVisitor visitor = new BigIncreaseVisitor();

        // act
        visitor.visit(offer);

        // assert
        assertThat(offer.getPrice())
                .isEqualTo(PRICE + PRICE * BIG_INCREASE_MUL);
    }

}
