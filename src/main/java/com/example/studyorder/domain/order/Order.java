package com.example.studyorder.domain.order;

import com.example.studyorder.common.exception.IllegalStatusException;
import com.example.studyorder.common.exception.InvalidParamException;
import com.example.studyorder.common.util.TokenGenerator;
import com.example.studyorder.domain.AbstractEntity;
import com.example.studyorder.domain.order.fragment.DeliveryFragment;
import com.example.studyorder.domain.order.item.OrderItem;
import com.google.common.collect.Lists;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AbstractEntity {

    private static final String ORDER_PREFIX = "ord_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String orderToken;
    private Long userId;
    private String payMethod;

    @Embedded
    private DeliveryFragment deliveryFragment;

    private ZonedDateTime orderedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        INIT("주문시작"),
        ORDER_COMPLETE("주문 완료"),
        DELIVERY_PREPARE("배송 준비"),
        IN_DELIVERY("배송 중"),
        DELIVERY_COMPLETE("배송 완료");

        private final String description;
    }

    @Builder
    public Order(Long userId, String payMethod, DeliveryFragment deliveryFragment) {
        if(userId == null) throw new InvalidParamException("order.userId");
        if(StringUtils.isEmpty(payMethod)) throw new InvalidParamException("order.payMethod");
        if(deliveryFragment == null) throw new InvalidParamException("order.deliveryFragment");

        this.orderToken = TokenGenerator.randomCharacterWithPrefix(ORDER_PREFIX);
        this.userId = userId;
        this.payMethod = payMethod;
        this.deliveryFragment = deliveryFragment;
        this.orderedAt = ZonedDateTime.now();
        this.status = Status.INIT;
    }

    public void orderComplete(){
        if(this.status == Status.INIT) throw new IllegalStatusException();
        this.status = Status.DELIVERY_COMPLETE;
    }

    public boolean isAlreadyPaymentComplete(){
        switch(this.status){
            case ORDER_COMPLETE:
            case DELIVERY_PREPARE:
            case IN_DELIVERY:
            case DELIVERY_COMPLETE:
                return true;
        }
        return false;
    }

    /**
     * 주문 가격 = 주문 상품의 총 가격
     * 주문 하나의 상품의 가격 = (상품 가격 + 상품 옵션 가격) * 주문 갯수
     */
    public Long calculateOrderPrice(){
        return orderItems.stream()
            .mapToLong(OrderItem::calculateOrderPrice)
            .sum();
    }
}
