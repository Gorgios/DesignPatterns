package pl.jreclaw.market.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.jreclaw.market.observer.OfferObserver;

@Getter
@Setter
@EqualsAndHashCode
public class Interest implements OfferObserver {
    private Offer offer;
    private Client client;
    @EqualsAndHashCode.Exclude
    private double interest;
    @EqualsAndHashCode.Exclude
    private double lastPrice;

    public Interest(Offer offer, Client client) {
        this.offer = offer;
        this.client = client;
        interest = Math.max(0.3, Math.random());
        lastPrice = offer.getPrice();
    }

    @Override
    public void update(Offer offer) {
        if (offer.isBought()) {
            client.unfollowOffer(offer);
        }
        double increase = (offer.getPrice() - lastPrice) / lastPrice;
        lastPrice = offer.getPrice();
        if (Math.abs(increase) > 0.05) {
            increaseInterest(increase);
            if (interest >= client.getRule()) {
                client.canBuyOffer(offer);
            }
        }
    }

    public void updateInflation(double inflationDiff) {
        if (Math.abs(inflationDiff) > 0.05) {
            increaseInterest(inflationDiff * interest);
        }
    }

    public void increaseInterest(double increase) {
        setInterest(interest - increase);
    }

    public void setInterest(double interest) {
        this.interest = Math.max(0, interest);
    }

    @Override
    public String toString() {
        return String.format("Produkt: %s, Cena: %.2f, Zainteresowanie %.2f",
                offer.getProduct(), offer.getPrice(), interest);
    }
}
