package com.moderntech.ecommerce.main;

import models.*;
import payment.*;

import java.util.*;

public class ECommerceApp {

    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     ДОБРО ПОЖАЛОВАТЬ В МАГАЗИН!");
        System.out.println("=".repeat(50));

        // Шаг 1: СОЗДАЁМ КАТАЛОГ ТОВАРОВ
        Map<String, Product> catalog = new HashMap<>();
        catalog.put("P001", new Product("P001", "iPhone 15", "Смартфон", 79900, 10));
        catalog.put("P002", new Product("P002", "MacBook Air", "Ноутбуки", 99900, 5));
        catalog.put("P003", new Product("P003", "Наушники Sony", "Аксессуары", 8900, 20));
        catalog.put("P004", new Product("P004", "Чехол для iPhone", "Аксессуары", 1500, 50));

        // Выводим каталог
        System.out.println("\n📋 КАТАЛОГ ТОВАРОВ:");
        System.out.println("  ─────────────────────────────────────────");
        for (Product p : catalog.values()) {
            System.out.printf("  %s | %s | %.0f руб. | %d шт.%n",
                    p.name(), p.category(), p.price(), p.stock());
        }
        System.out.println("  ─────────────────────────────────────────");

        // Шаг 2: СОЗДАЁМ ПОКУПАТЕЛЯ
        Customer customer = new Customer("C001", "Анна Иванова", "anna@mail.ru");
        System.out.println("\n👤 ПОКУПАТЕЛЬ: " + customer);

        // Шаг 3: СОЗДАЁМ КОРЗИНУ И ДОБАВЛЯЕМ ТОВАРЫ
        ShoppingCart cart = new ShoppingCart(catalog);
        System.out.println("\n🛒 ДОБАВЛЕНИЕ ТОВАРОВ:");
        cart.addItem("P001", 1);  // iPhone
        cart.addItem("P003", 2);  // Наушники x2
        cart.addItem("P004", 1);  // Чехол

        // Шаг 4: ПОКАЗЫВАЕМ КОРЗИНУ
        System.out.println("\n📦 ВАША КОРЗИНА:");
        cart.print();

        // Шаг 5: ОФОРМЛЯЕМ ЗАКАЗ
        System.out.println("\n✅ ОФОРМЛЕНИЕ ЗАКАЗА...");
        Order order = new Order(customer.getId(), cart, catalog);
        order.print();

        // Шаг 6: МЕНЯЕМ СТАТУС
        System.out.println("\n📬 ИЗМЕНЕНИЕ СТАТУСА:");
        order.updateStatus("CONFIRMED");
        order.updateStatus("PROCESSING");
        order.updateStatus("SHIPPED");

        // Шаг 7: ТРИ СЦЕНАРИЯ ОПЛАТЫ
        System.out.println("\n💳 СЦЕНАРИИ ОПЛАТЫ:");

        System.out.println("\n  --- 1. Ozon + Банковская карта ---");
        PaymentMethod ozonCard = new OzonPayment("Банковская карта");
        ozonCard.processPayment(order);

        System.out.println("\n  --- 2. Wildberries + Электронный кошелёк ---");
        PaymentMethod wbWallet = new WildberriesPayment("Электронный кошелёк");
        wbWallet.processPayment(order);

        System.out.println("\n  --- 3. Ozon + Наложенный платёж ---");
        PaymentMethod ozonCash = new OzonPayment("Наложенный платёж");
        ozonCash.processPayment(order);

        // Шаг 8: ИТОГОВАЯ СВОДКА
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     ИТОГОВАЯ СВОДКА");
        System.out.println("=".repeat(50));
        order.print();

        System.out.println("\n✨ СПАСИБО ЗА ПОКУПКУ! ✨\n");
    }
}