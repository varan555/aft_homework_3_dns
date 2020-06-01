package Pages;

import java.util.HashMap;

public class VirtualCart {

    private static HashMap<String, Double> purchase = new HashMap<>();

    public static void addPurchase(String name, Double price) {
        purchase.put(name, price);
    }

    public static HashMap<String, Double> getPurchase() {
        return purchase;
    }

    public static void removePurchase(String name) {
        purchase.remove(name);
    }

    public static void setPurchase(HashMap<String, Double> purchase) {
        VirtualCart.purchase = purchase;
    }
}
