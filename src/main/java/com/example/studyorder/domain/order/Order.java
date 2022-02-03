package com.example.studyorder.domain.order;

import com.example.studyorder.common.exception.IllegalStatusException;
import com.example.studyorder.common.exception.InvalidParamException;
import com.example.studyorder.domain.AbstractEntity;
import com.example.studyorder.domain.order.item.OrderItem;
import com.google.common.collect.Lists;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.CascadeType;
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
@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String orderToken;
    private Long userId;
    private String payMethod;

    private String receiverName;
    private String receiverPhone;
    private String receiverZipcode;
    private String receiverAddress1;
    private String receiverAddress2;
    private String etcMessage;

    private ZonedDateTime orderAt;

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
    public Order(Long userId, String payMethod, String receiverName, String receiverPhone, String receiverZipcode, String receiverAddress1, String receiverAddress2, String etcMessage) {
        if(userId == null) throw new InvalidParamException("order.userId");
        if(StringUtils.isEmpty(payMethod)) throw new InvalidParamException("order.payMethod");
        if(StringUtils.isEmpty(receiverName)) throw new InvalidParamException("order.receiverName");
        if(StringUtils.isEmpty(receiverPhone)) throw new InvalidParamException("order.receiverPhone");
        if(StringUtils.isEmpty(receiverZipcode)) throw new InvalidParamException("order.receiverZipcode");
        if(StringUtils.isEmpty(receiverAddress1)) throw new InvalidParamException("order.receiverAddress1");
        if(StringUtils.isEmpty(receiverAddress2)) throw new InvalidParamException("order.receiverAddress2");
        if(StringUtils.isEmpty(etcMessage)) throw new InvalidParamException("order.etcMessage");
        this.userId = userId;
        this.payMethod = payMethod;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverZipcode = receiverZipcode;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.etcMessage = etcMessage;
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
