package pl.jreclaw.market.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.jreclaw.market.observer.InflationObserver;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public class Client implements InflationObserver {
    @EqualsAndHashCode.Include
    private String name;
    private List<Interest> interests;
    private Bank bank;
    private double rule;
    private double lastKnownInflation;
    private double money;
    private List<Product> boughtProducts;


    public Client(String name, double rule) {
        this.name = name;
        this.interests = new CopyOnWriteArrayList<>();
        this.rule = rule;
        this.bank = Bank.getInstance();
        this.lastKnownInflation = bank.getInflation();
        this.boughtProducts = new ArrayList<>();
        this.money = 10000D;
        this.bank.addFollower(this);
    }

    public Client(String name) {
        this(name, 0.75);
    }

    public void addProductToBought(Product product) {
        boughtProducts.add(product);
    }

    public void followOffer(Offer offer) {
        Interest interest = new Interest(offer, this);
        interests.add(interest);
        offer.addFollower(interest);
    }

    public void unfollowOffer(Offer offer) {
        interests.removeIf(e -> offer.equals(e.getOffer()));
    }

    public void spendMoney(double amount) {
        money -= amount;
    }

    @Override
    public void update(double inflation) {
        double inflationDiff = inflation - lastKnownInflation;
        lastKnownInflation = inflation;
        interests.forEach(e -> e.updateInflation(inflationDiff));
    }

    public void canBuyOffer(Offer offer) {
        if (offer.getPrice() <= money) {
            offer.buy(this);
        }
    }
}
