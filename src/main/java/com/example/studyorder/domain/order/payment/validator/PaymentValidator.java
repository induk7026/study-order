package com.example.studyorder.domain.order.payment.validator;

import com.example.studyorder.domain.order.Order;
import com.example.studyorder.domain.order.OrderCommand;

public interface PaymentValidator {
    void validator(Order order, OrderCommand.PaymentRequest paymentRequest);
}
