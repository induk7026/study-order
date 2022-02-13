package com.example.studyorder.domain.order.payment.validator;

import com.example.studyorder.common.exception.InvalidParamException;
import com.example.studyorder.domain.order.Order;
import com.example.studyorder.domain.order.OrderCommand.PaymentRequest;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 2)
@Component
public class PayMethodValidator implements PaymentValidator{

    @Override
    public void validator(Order order, PaymentRequest paymentRequest) {
        if(order.getPayMethod().equals(paymentRequest.getPayMethod().name())){
            throw new InvalidParamException("주문 과정에서의 결제수단이 다릅니다.");
        }
    }
}
