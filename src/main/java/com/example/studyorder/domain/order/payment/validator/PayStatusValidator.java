package com.example.studyorder.domain.order.payment.validator;

import com.example.studyorder.common.exception.InvalidParamException;
import com.example.studyorder.domain.order.Order;
import com.example.studyorder.domain.order.OrderCommand.PaymentRequest;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 3)
@Component
public class PayStatusValidator implements PaymentValidator{

    @Override
    public void validator(Order order, PaymentRequest paymentRequest) {
        if(order.isAlreadyPaymentComplete()) throw new InvalidParamException("이미 결제된 주문건입니다.");
    }
}
