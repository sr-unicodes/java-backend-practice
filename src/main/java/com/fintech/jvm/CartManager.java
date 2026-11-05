package com.fintech.jvm;

import java.util.*;

public class CartManager {

    private Set<String> usedPromos = new HashSet<>();
    private Map<String, Integer> userCarts = new HashMap<>();
    private Deque<String> refundHistory = new ArrayDeque<>();

    public void applyPromo(String code) {
        if (usedPromos.contains(code)) {
            System.out.println("Already used: " + code);
        } else {
            usedPromos.add(code);
            System.out.println("Promo applied: " + code);
        }
    }

    public void addToCart(String userId, int amount) {
        int currentTotal = userCarts.getOrDefault(userId, 0);
        userCarts.put(userId, currentTotal + amount);
        System.out.println(userId + " cart total is now: $" + userCarts.get(userId));
    }

    public void processRefund(String transactionId) {
        refundHistory.push(transactionId);
        System.out.println("Refund logged for: " + transactionId);
    }

    public void undoLastRefund() {
        if (refundHistory.isEmpty()) {
            System.out.println("No refunds to undo.");
            return;
        }
        String lastRefund = refundHistory.pop();
        System.out.println("UNDONE - Reversed refund for: " + lastRefund);
    }

    public static void main(String[] args) {
        CartManager engine = new CartManager();

        System.out.println("--- PROMO TEST ---");
        engine.applyPromo("SAVE20");
        engine.applyPromo("SAVE20");

        System.out.println("\n--- CART TEST ---");
        engine.addToCart("User_Alice", 500);
        engine.addToCart("User_Alice", 200);

        System.out.println("\n--- REFUND TEST ---");
        engine.processRefund("TXN-999");
        engine.processRefund("TXN-888");
        engine.undoLastRefund();
    }
}