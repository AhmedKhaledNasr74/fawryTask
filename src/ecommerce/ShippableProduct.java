package ecommerce;

public class ShippableProduct extends Product implements Shippable {
    private final double weight;

    public ShippableProduct(String name, double price, int qty, double weight) {
        super(name, price, qty);
        this.weight = weight;
    }

    @Override public double getWeight() { return weight; }
}
