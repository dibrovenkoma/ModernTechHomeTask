package payment;

import models.Order;

public sealed interface PaymentMethod permits OzonPayment, WildberriesPayment {
    boolean processPayment(Order order);
    String getMethodName();
}