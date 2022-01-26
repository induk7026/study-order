package com.example.studyorder.domain.item.optiongroup;

import com.example.studyorder.common.exception.InvalidParamException;
import com.example.studyorder.domain.AbstractEntity;
import com.example.studyorder.domain.item.Item;
import com.example.studyorder.domain.item.option.ItemOption;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.Entity;
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
import org.apache.commons.lang3.StringUtils;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "optionGroups")
public class ItemOptionGroup extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ordering;
    private String itemOptionGroupName;

    //JoinColumn 외래키를 매핑 할 때 사용
    //필드명 _ id 참조하는 기본키(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 연관관계에서 주인을 정하는 것은 mappedBy 설정
    // 주인은 mappedBy 사용 x
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup")
    private List<ItemOption> itemOptions = Lists.newArrayList();;

    @Builder
    public ItemOptionGroup(Integer ordering, String itemOptionGroupName, Item item) {
        if(ordering == null) throw new InvalidParamException("ordering is empty");
        if(StringUtils.isEmpty(itemOptionGroupName)) throw new InvalidParamException("itemOptionGroupName is empty");
        if(item == null) throw new InvalidParamException("item is empty");
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
        this.item = item;
    }
}
