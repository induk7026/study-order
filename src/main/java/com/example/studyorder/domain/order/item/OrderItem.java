package com.example.studyorder.domain.order.item;

import com.example.studyorder.common.exception.InvalidParamException;
import com.example.studyorder.domain.order.Order;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer orderCount;
    private Long partnerId;
    private Long itemId;
    private String itemName;
    private String itemToken;
    private Long itemPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItem", cascade = CascadeType.PERSIST)
    private List<OrderItemOptionGroup> orderItemOptionGroups = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Getter
    @RequiredArgsConstructor
    public enum DeliveryStatus {
        BEFORE_DELIVERY("배송전"),
        DELIVERY_PREPARE("배송준비중"),
        DELIVERING("배송중"),
        COMPLETE_DELIVERY("배송완료");

        private final String description;
    }

    @Builder
    public OrderItem(Order order, Integer orderCount, Long partnerId, Long itemId, String itemName, String itemToken, Long itemPrice) {
        if(order == null) throw new InvalidParamException("orderItem.order");
        if(partnerId == null) throw new InvalidParamException("orderItem.partnerId");
        if (itemId == null && StringUtils.isEmpty(itemName))
            throw new InvalidParamException("OrderItemLine.itemNo and itemName");
        if(itemPrice == null) throw new InvalidParamException("orderItem.itemPrice");
        if(StringUtils.isEmpty(itemToken)) throw new InvalidParamException("orderItem.itemToken");
        this.order = order;
        this.orderCount = orderCount;
        this.partnerId = partnerId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemToken = itemToken;
        this.itemPrice = itemPrice;
        this.status = DeliveryStatus.BEFORE_DELIVERY;
    }

    public Long calculateOrderPrice(){
        var itemOrderGroupPrice =  orderItemOptionGroups
            .stream()
            .mapToLong(OrderItemOptionGroup::calculateTotalAmount)
            .sum();
        return (itemPrice + itemOrderGroupPrice) * orderCount;
    }
}
