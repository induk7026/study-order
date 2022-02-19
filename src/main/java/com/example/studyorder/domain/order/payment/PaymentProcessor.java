package com.example.studyorder.domain.order.payment;

import com.example.studyorder.domain.order.Order;
import com.example.studyorder.domain.order.OrderCommand;

public interface PaymentProcessor {
    void pay(Order order, OrderCommand.PaymentRequest paymentRequest);
}
