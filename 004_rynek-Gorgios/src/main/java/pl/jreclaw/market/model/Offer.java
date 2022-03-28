package pl.jreclaw.market.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.jreclaw.market.observer.OfferObserver;
import pl.jreclaw.market.visitor.MarketVisitor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
@EqualsAndHashCode
public class Offer {
    private final Product product;
    @EqualsAndHashCode.Include
    private Seller seller;
    @EqualsAndHashCode.Exclude
    private double price;
    private final double productionCost;
    @EqualsAndHashCode.Exclude
    private List<OfferObserver> followers;
    boolean bought;

    public Offer(Product product, double price, double productionCost) {
        this.product = product;
        this.price = price;
        this.productionCost = productionCost;
        this.followers = new CopyOnWriteArrayList<>();
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void accept(MarketVisitor visitor) {
        visitor.visit(this);
    }

    public void buy(Client client) {
        client.addProductToBought(this.product);
        client.spendMoney(price);
        bought = true;
        for (OfferObserver follower : followers) {
            follower.update(this);
            followers.remove(follower);
        }
        followers = null;
        seller.makeMoney(price - productionCost);
        seller.getOffers().remove(this);
    }

    public void addFollower(OfferObserver observer) {
        followers.add(observer);
    }

    public void removeFollower(OfferObserver observer) {
        followers.remove(observer);
    }

    public void increasePriceByPercent(double mul) {
        this.setPrice(price + price * mul);
        for (OfferObserver follower : followers) {
            follower.update(this);
        }
    }

    public void setPrice(double price){
        this.price = Math.max(price,0);
    }

    @Override
    public String toString(){
        return "\nProdukt: " + product.getName() + "\tCena: " + String.format("%.2f",price);
    }
}
