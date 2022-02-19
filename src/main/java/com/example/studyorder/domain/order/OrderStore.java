package com.example.studyorder.domain.order;

import com.example.studyorder.domain.order.item.OrderItem;
import com.example.studyorder.domain.order.OrderInfo.OrderItemOption;
import com.example.studyorder.domain.order.OrderInfo.OrderItemOptionGroup;

public interface OrderStore {
    Order store(Order order);
    OrderItem store(OrderItem orderItem);
    OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup);
    OrderItemOption store(OrderItemOption orderItemOption);
}
