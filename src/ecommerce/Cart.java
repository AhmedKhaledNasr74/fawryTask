package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void add(Product p, int qty) {
        if (qty <= 0 || qty > p.getQuantity())
            throw new IllegalArgumentException("Invalid quantity for " + p.getName());
        items.add(new CartItem(p, qty));
    }

    public List<CartItem> getItems() { return items; }
    public boolean isEmpty()         { return items.isEmpty(); }
}
