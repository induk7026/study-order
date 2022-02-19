package com.example.studyorder.domain.order;

import com.example.studyorder.domain.item.Item;
import com.example.studyorder.domain.order.fragment.DeliveryFragment;
import com.example.studyorder.domain.order.item.OrderItem;
import com.example.studyorder.domain.order.item.OrderItemOption;
import com.example.studyorder.domain.order.item.OrderItemOptionGroup;
import com.example.studyorder.domain.order.payment.PayMethod;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class OrderCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterOrder {
        private Long userId;
        private String payMethod;
        private String receiverName;
        private String receiverPhone;
        private String receiverZipcode;
        private String receiverAddress1;
        private String receiverAddress2;
        private String etcMessage;
        private final List<RegisterOrderItem> orderItems;

        public Order toEntity(){
            var deliveryFragment = DeliveryFragment.builder()
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverZipcode(receiverZipcode)
                .receiverAddress1(receiverAddress1)
                .receiverAddress2(receiverAddress2)
                .etcMessage(etcMessage)
                .build();

            return Order.builder()
                .userId(userId)
                .payMethod(payMethod)
                .deliveryFragment(deliveryFragment)
                .build();
        }

    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItem {
        private final Integer orderCount;
        private final String itemToken;
        private final String itemName;
        private final Long itemPrice;
        private final List<RegisterOrderItemOptionGroup> orderItemOptionGroups;

        public OrderItem toEntity(Order order, Item item) {
            return OrderItem.builder()
                .order(order)
                .orderCount(orderCount)
                .partnerId(item.getPartnerId())
                .itemId(item.getId())
                .itemToken(itemToken)
                .itemName(itemName)
                .itemPrice(itemPrice)
                .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItemOptionGroup {
        private final Integer ordering;
        private final String itemOptionGroupName;
        private final List<RegisterOrderItemOption> orderItemOptions;

        public OrderItemOptionGroup toEntity(OrderItem orderItem) {
            return OrderItemOptionGroup.builder()
                .orderItem(orderItem)
                .ordering(ordering)
                .itemOptionGroupName(itemOptionGroupName)
                .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItemOption {
        private final Integer ordering;
        private final String itemOptionName;
        private final Long itemOptionPrice;

        public OrderItemOption toEntity(OrderItemOptionGroup orderItemOptionGroup) {
            return OrderItemOption.builder()
                .orderItemOptionGroup(orderItemOptionGroup)
                .ordering(ordering)
                .itemOptionName(itemOptionName)
                .itemOptionPrice(itemOptionPrice)
                .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class PaymentRequest {
        private final String orderToken;
        private final Long amount;
        private final PayMethod payMethod;
    }
}
