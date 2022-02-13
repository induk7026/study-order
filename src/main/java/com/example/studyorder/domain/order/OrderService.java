package com.example.studyorder.domain.order;

import com.example.studyorder.domain.order.payment.OrderInfo;

public interface OrderService {
    String registerOrder(OrderCommand.RegisterOrder registerOrder);
    void paymentOrder(OrderCommand.PaymentRequest paymentRequest);
    OrderInfo.Main retrieveOrder(String orderToken);
}
