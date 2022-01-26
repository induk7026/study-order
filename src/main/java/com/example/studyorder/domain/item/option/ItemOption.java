package com.example.studyorder.domain.item.option;

import com.example.studyorder.common.exception.InvalidParamException;
import com.example.studyorder.domain.AbstractEntity;
import com.example.studyorder.domain.item.optiongroup.ItemOptionGroup;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "itemOptions")
public class ItemOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ordering;
    private String itemOptionName;
    private Long itemOptionPrice;

    @ManyToOne
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroup itemOptionGroup;

    @Builder
    public ItemOption(Integer ordering, String itemOptionName, Long itemOptionPrice,
        ItemOptionGroup itemOptionGroup) {
        if(ordering == null) throw new InvalidParamException("ordering is empty");
        if(StringUtils.isEmpty(itemOptionName)) throw new InvalidParamException("itemOptionName is empty");
        if(itemOptionPrice == null) throw new InvalidParamException("itemOptionPrice is empty");
        if(itemOptionGroup == null) throw new InvalidParamException("itemOptionGroup is empty");
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
        this.itemOptionGroup = itemOptionGroup;
    }
}
