package pl.jreclaw.market.model;

import lombok.Getter;
import lombok.Setter;
import pl.jreclaw.market.observer.InflationObserver;
import pl.jreclaw.market.visitor.MarketVisitor;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public class Seller implements InflationObserver {
    private final String name;
    private final List<Offer> offers;
    private double lastKnownInflation;
    private double money;
    private final Bank bank;

    public void addOffer(Offer offer) {
        offer.setSeller(this);
        offers.add(offer);
    }

    public Seller(String name) {
        this.name = name;
        this.offers = new CopyOnWriteArrayList<>();
        this.bank = Bank.getInstance();
        this.lastKnownInflation = bank.getInflation();
        this.bank.addFollower(this);
    }

    public void makeMoney(double amount) {
        bank.update(amount);
        setMoney(money + amount);
    }

    @Override
    public void update(double inflation) {
        double diff = inflation - lastKnownInflation;
        if (Math.abs(diff) > 0.05) {
            lastKnownInflation = inflation;
            offers.forEach(e -> e.setPrice(e.getPrice() + e.getPrice() * diff));
        }
    }

    public void acceptMarketChanges(MarketVisitor visitor) {
        for (Offer offer : offers) {
            offer.accept(visitor);
        }
    }
}
