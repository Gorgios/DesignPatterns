package pl.jreclaw.market.visitor;

import pl.jreclaw.market.model.Offer;

public interface MarketVisitor {
    void visit(Offer offer);
}
