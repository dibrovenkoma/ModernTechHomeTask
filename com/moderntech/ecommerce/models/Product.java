package models;

public record Product(
        String id,
        String name,
        String category,
        double price,
        int stock
) {
    public Product {
        if (price < 0) throw new IllegalArgumentException("Цена не может быть отрицательной");
        if (stock < 0) throw new IllegalArgumentException("Склад не может быть отрицательным");
    }

    public boolean isAvailable(int quantity) {
        return stock >= quantity;
    }
}