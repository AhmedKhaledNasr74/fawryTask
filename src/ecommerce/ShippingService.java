package ecommerce;

import java.util.*;

public class ShippingService {
    public static void ship(List<Shippable> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        Map<String,Integer> counts  = new LinkedHashMap<>();
        Map<String,Double>  weights = new HashMap<>();

        for (Shippable s : items) {
            counts.merge(s.getName(), 1, Integer::sum);
            weights.putIfAbsent(s.getName(), s.getWeight());
            totalWeight += s.getWeight();
        }

        counts.forEach((name, qty) ->
                System.out.printf("%dx %s %.0fg%n", qty, name, weights.get(name) * 1000)
        );

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
