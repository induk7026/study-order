package com.example.studyorder.domain.order.fragment;

import com.example.studyorder.common.exception.InvalidParamException;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Getter
@Embeddable
@NoArgsConstructor
public class DeliveryFragment {
    private String receiverName;
    private String receiverPhone;
    private String receiverZipcode;
    private String receiverAddress1;
    private String receiverAddress2;
    private String etcMessage;

    @Builder
    public DeliveryFragment(String receiverName, String receiverPhone, String receiverZipcode, String receiverAddress1, String receiverAddress2, String etcMessage) {
        if(StringUtils.isEmpty(receiverName)) throw new InvalidParamException("order.receiverName");
        if(StringUtils.isEmpty(receiverPhone)) throw new InvalidParamException("order.receiverPhone");
        if(StringUtils.isEmpty(receiverZipcode)) throw new InvalidParamException("order.receiverZipcode");
        if(StringUtils.isEmpty(receiverAddress1)) throw new InvalidParamException("order.receiverAddress1");
        if(StringUtils.isEmpty(receiverAddress2)) throw new InvalidParamException("order.receiverAddress2");
        if(StringUtils.isEmpty(etcMessage)) throw new InvalidParamException("order.etcMessage");
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverZipcode = receiverZipcode;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.etcMessage = etcMessage;
    }
}
