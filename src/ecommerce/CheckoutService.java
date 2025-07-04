package ecommerce;

import java.util.*;

public class CheckoutService {
    private static final double FLAT_SHIPPING_FEE = 30.0;

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty.");
        }

        double subTotal = 0.0;
        List<Shippable> shippableItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            // Expiry check
            if (product instanceof Expirable expirableProduct && expirableProduct.isExpired()) {
                throw new RuntimeException(product.getName() + " is expired.");
            }

            // Stock check
            if (cartItem.getQuantity() > product.getQuantity()) {
                throw new RuntimeException(product.getName() + " is out of stock.");
            }

            subTotal += cartItem.getTotal();

            // Collect for shipping
            if (product instanceof Shippable shippableProduct) {
                shippableItems.addAll(Collections.nCopies(cartItem.getQuantity(), shippableProduct));
            }
        }

        double shippingFee = shippableItems.isEmpty() ? 0.0 : FLAT_SHIPPING_FEE;
        double totalAmount = subTotal + shippingFee;

        // Balance check
        if (customer.getBalance() < totalAmount) {
            throw new RuntimeException("Insufficient balance.");
        }

        // Commit stock & payment
        cart.getItems().forEach(item -> item.getProduct().reduceQuantity(item.getQuantity()));
        customer.deduct(totalAmount);

        // Ship if needed
        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        // Receipt
        System.out.println("** Checkout receipt **");
        cart.getItems().forEach(item ->
                System.out.printf("%dx %s %.0f%n",
                        item.getQuantity(),
                        item.getProduct().getName(),
                        item.getTotal())
        );
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n",  subTotal);
        System.out.printf("Shipping %.0f%n",  shippingFee);
        System.out.printf("Amount   %.0f%n",  totalAmount);
        System.out.printf("Balance  %.0f%n",  customer.getBalance());
    }
}
