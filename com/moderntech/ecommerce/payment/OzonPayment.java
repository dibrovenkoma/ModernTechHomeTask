package payment;

import models.Order;

public final class OzonPayment implements PaymentMethod {
    private final String paymentType;

    public OzonPayment(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean processPayment(Order order) {
        System.out.println("  Обработка платежа через Ozon (" + paymentType + ")");
        System.out.println("  Сумма: " + order.getTotal() + " руб.");
        System.out.println("  ✓ Платёж успешно выполнен!");
        return true;
    }

    @Override
    public String getMethodName() {
        return "Ozon - " + paymentType;
    }
}