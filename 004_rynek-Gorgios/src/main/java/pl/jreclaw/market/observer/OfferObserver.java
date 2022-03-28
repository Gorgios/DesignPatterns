package pl.jreclaw.market.observer;

import pl.jreclaw.market.model.Offer;

public interface OfferObserver {
    void update(Offer offer);
}
