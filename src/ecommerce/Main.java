package ecommerce;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);
        Date future = cal.getTime();

        // Products
        Product cheese       = new ExpiringShippableProduct("Cheese",        100, 10, future, 0.2);
        Product biscuits     = new ExpiringShippableProduct("Biscuits",      150,  5, future, 0.7);
        Product tv           = new ShippableProduct        ("TV",           5000,  3, 5.0);
        Product scratchCard  = new DigitalProduct          ("Scratch Card",   50, 20);

        Customer customer = new Customer("Ahmed", 1000);
        Cart cart = new Cart();
        cart.add(cheese, 2);        // 200
        cart.add(biscuits, 1);      // 150
        cart.add(scratchCard, 1);   //  50

        CheckoutService.checkout(customer, cart);
    }
}
