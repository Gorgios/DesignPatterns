package pl.jreclaw.market.visitor;

import pl.jreclaw.market.model.Offer;

public class BigIncreaseVisitor implements MarketVisitor{
    @Override
    public void visit(Offer offer) {
        offer.increasePriceByPercent(0.15);
    }

}
