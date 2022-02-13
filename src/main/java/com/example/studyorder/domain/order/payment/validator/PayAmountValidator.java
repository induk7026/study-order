package com.example.studyorder.domain.order.payment.validator;

import com.example.studyorder.common.exception.InvalidParamException;
import com.example.studyorder.domain.order.Order;
import com.example.studyorder.domain.order.OrderCommand.PaymentRequest;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 1)
@Component
public class PayAmountValidator implements PaymentValidator{

    @Override
    public void validator(Order order, PaymentRequest paymentRequest) {
        if(!order.calculateOrderPrice().equals(paymentRequest.getAmount())){
            throw new InvalidParamException("주문 가격이 불일치합니다.");
        }
    }
}
