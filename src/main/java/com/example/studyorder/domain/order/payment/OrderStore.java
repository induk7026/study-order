package com.example.studyorder.domain.order.payment;

import com.example.studyorder.domain.order.Order;
import com.example.studyorder.domain.order.item.OrderItem;
import com.example.studyorder.domain.order.payment.OrderInfo.OrderItemOption;
import com.example.studyorder.domain.order.payment.OrderInfo.OrderItemOptionGroup;

public interface OrderStore {
    Order store(Order order);
    OrderItem store(OrderItem orderItem);
    OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup);
    OrderItemOption store(OrderItemOption orderItemOption);
}
