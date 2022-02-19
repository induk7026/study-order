package com.example.studyorder.domain.order;

import com.example.studyorder.domain.order.item.OrderItem;
import com.example.studyorder.domain.order.item.OrderItemOption;
import com.example.studyorder.domain.order.item.OrderItemOptionGroup;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedSourcePolicy = ReportingPolicy.ERROR
)
public interface OrderInfoMapper {

    @Mappings({
        @Mapping(source = "order.id", target = "orderId"),
        @Mapping(source = "order.deliveryFragment", target = "deliveryInfo"),
        @Mapping(expression = "java(order.calculateTotalAmount())", target = "totalAmount"),
        @Mapping(expression = "java(order.getStatus().name())", target = "status"),
        @Mapping(expression = "java(order.getStatus().getDescription())", target = "statusDescription")
    })
    OrderInfo.Main of(Order order, List<OrderItem> orderItems);

    @Mappings({
        @Mapping(expression = "java(orderItem.getDeliveryStatus().name())", target = "deliveryStatus"),
        @Mapping(expression = "java(orderItem.getDeliveryStatus().getDescription())", target = "deliveryStatusDescription"),
        @Mapping(expression = "java(orderItem.calculateTotalAmount())", target = "totalAmount")
    })
    OrderInfo.OrderItem of(OrderItem orderItem);

    OrderInfo.OrderItemOptionGroup of(OrderItemOptionGroup orderItemOptionGroup);

    OrderInfo.OrderItemOption of(OrderItemOption orderItemOption);
}
