package com.example.studyorder.domain.order;

import com.example.studyorder.domain.order.OrderInfo.OrderItem;
import java.util.List;

public interface OrderItemSeriesFactory {
    List<OrderItem> store(Order order, OrderCommand.RegisterOrder registerOrder);
}
