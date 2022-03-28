package pl.jreclaw.market.model;

import lombok.Data;
import pl.jreclaw.market.observer.InflationObserver;
import pl.jreclaw.market.observer.TurnoverObserver;

import java.util.*;

@Data
public class Bank implements TurnoverObserver {
    private static Bank instance;
    private double inflation;
    private List<InflationObserver> followers;
    private double avgIncome;
    private double money;
    private double transactions;

    public void addFollower(InflationObserver observer) {
        followers.add(observer);
    }

    private Bank() {
        // base inflation
        inflation = 2.0;
        followers = new ArrayList<>();
    }

    @Override
    public void update(double amount) {
        transactions++;
        money += amount;
        avgIncome = money / transactions;
        if (amount >= 1.2 * avgIncome) {
            setInflation(inflation + 0.1);
        } else if (amount <= 0.8 * avgIncome) {
            setInflation(inflation - 0.1);
        }
    }

    public void setInflation(double value) {
        this.inflation = Math.max(0,value);
        followers.forEach(e -> e.update(value));
    }

    public static Bank getInstance() {
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
