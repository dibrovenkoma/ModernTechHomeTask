package models;

import java.util.*;

public class ShoppingCart {
    private final Map<String, Integer> items;
    private final Map<String, Product> catalog;

    public ShoppingCart(Map<String, Product> catalog) {
        this.items = new HashMap<>();
        this.catalog = catalog;
    }

    public void addItem(String productId, int quantity) {
        Product product = catalog.get(productId);
        if (product == null) {
            System.out.println("  ❌ Товар не найден!");
            return;
        }

        if (!product.isAvailable(quantity)) {
            System.out.println("  ❌ Недостаточно товара на складе!");
            return;
        }

        items.put(productId, items.getOrDefault(productId, 0) + quantity);
        System.out.println("  ✓ Добавлен: " + product.name() + " x" + quantity);
    }

    public void removeItem(String productId) {
        if (items.remove(productId) != null) {
            System.out.println("  ✓ Товар удалён из корзины");
        }
    }

    public double getSubtotal() {
        double total = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            Product product = catalog.get(entry.getKey());
            total += product.price() * entry.getValue();
        }
        return total;
    }

    public double getVAT() {
        return getSubtotal() * 0.20;
    }

    public double getTotal() {
        return getSubtotal() + getVAT();
    }

    public void print() {
        if (items.isEmpty()) {
            System.out.println("  Корзина пуста");
            return;
        }

        System.out.println("  Содержимое корзины:");
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            Product product = catalog.get(entry.getKey());
            double itemTotal = product.price() * entry.getValue();
            System.out.printf("    %s x%d = %.2f руб.%n",
                    product.name(), entry.getValue(), itemTotal);
        }
        System.out.printf("  Сумма без НДС: %.2f руб.%n", getSubtotal());
        System.out.printf("  НДС (20%%): %.2f руб.%n", getVAT());
        System.out.printf("  ИТОГО: %.2f руб.%n", getTotal());
    }

    public Map<String, Integer> getItems() {
        return new HashMap<>(items);
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}