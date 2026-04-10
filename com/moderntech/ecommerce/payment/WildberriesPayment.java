package payment;

import models.Order;

public final class WildberriesPayment implements PaymentMethod {
    private final String walletType;

    public WildberriesPayment(String walletType) {
        this.walletType = walletType;
    }

    @Override
    public boolean processPayment(Order order) {
        System.out.println("  Обработка платежа через Wildberries (" + walletType + ")");
        System.out.println("  Сумма: " + order.getTotal() + " руб.");
        System.out.println("  ✓ Платёж успешно выполнен!");
        return true;
    }

    @Override
    public String getMethodName() {
        return "Wildberries - " + walletType;
    }
}