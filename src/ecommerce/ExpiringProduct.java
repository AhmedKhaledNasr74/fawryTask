package ecommerce;

import java.util.Date;

public class ExpiringProduct extends Product implements Expirable {
    protected Date expiryDate;

    public ExpiringProduct(String name, double price, int qty, Date expiryDate) {
        super(name, price, qty);
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return new Date().after(expiryDate);
    }
}
