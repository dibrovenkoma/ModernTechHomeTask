package models;

import java.time.LocalDateTime;
import java.util.*;

public class Order {
    private final String orderId;
    private final String customerId;
    private final LocalDateTime date;
    private String status;
    private final List<OrderItem> items;
    private final double total;

    public record OrderItem(String productName, int quantity, double price, double subtotal) {}

    public Order(String customerId, ShoppingCart cart, Map<String, Product> catalog) {
        this.orderId = "ORD-" + System.currentTimeMillis();
        this.customerId = customerId;
        this.date = LocalDateTime.now();
        this.status = "PENDING";
        this.items = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : cart.getItems().entrySet()) {
            Product product = catalog.get(entry.getKey());
            items.add(new OrderItem(
                    product.name(),
                    entry.getValue(),
                    product.price(),
                    product.price() * entry.getValue()
            ));
        }

        this.total = cart.getTotal();
    }

    public void updateStatus(String newStatus) {
        System.out.println("  [Статус заказа: " + status + " → " + newStatus + "]");
        this.status = newStatus;
    }

    public void print() {
        System.out.println("  ═══════════════════════════════════════");
        System.out.println("    ЗАКАЗ №" + orderId);
        System.out.println("    Дата: " + date);
        System.out.println("    Статус: " + status);
        System.out.println("  ───────────────────────────────────────");
        for (OrderItem item : items) {
            System.out.printf("    %s x%d = %.2f руб.%n",
                    item.productName(), item.quantity(), item.subtotal());
        }
        System.out.println("  ───────────────────────────────────────");
        System.out.printf("    ВСЕГО: %.2f руб.%n", total);
        System.out.println("  ═══════════════════════════════════════");
    }

    public String getOrderId() { return orderId; }
    public double getTotal() { return total; }
}