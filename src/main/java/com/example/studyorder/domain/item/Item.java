package com.example.studyorder.domain.item;

import com.example.studyorder.common.exception.InvalidParamException;
import com.example.studyorder.common.util.TokenGenerator;
import com.example.studyorder.domain.AbstractEntity;
import com.example.studyorder.domain.item.optiongroup.ItemOptionGroup;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Getter
@Entity
@NoArgsConstructor
@Table(name ="items")
public class Item extends AbstractEntity {

    private static final String ITEM_PREFIX = "item_";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemToken;
    private Long partnerId;
    private String itemName;
    private Long itemPrice;
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "itemId")
    private List<ItemOptionGroup> optionGroups;

    @Builder
    public Item(Long partnerId, String itemName, Long itemPrice) {
        if (partnerId == null) throw new InvalidParamException("Item.partnerId");
        if (StringUtils.isBlank(itemName)) throw new InvalidParamException("Item.itemName");
        if (itemPrice == null) throw new InvalidParamException("Item.itemPrice");

        this.partnerId = partnerId;
        this.itemToken = TokenGenerator.randomCharacterWithPrefix(ITEM_PREFIX);
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.status = Status.PREPARE;
    }


    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PREPARE("판매준비중"),
        ON_SALES("판매중"),
        END_OF_SALES("판매종료");

        private final String description;
    }

    public void changePrePare(){
        status = Status.PREPARE;
    }

    public void changeOnSales(){
        if(status == Status.END_OF_SALES) throw new InvalidParamException();
        status = Status.ON_SALES;
    }

    public void changeEndOfSales(){
        status = Status.END_OF_SALES;
    }
}
