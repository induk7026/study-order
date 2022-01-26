package com.example.studyorder.domain.item;

import com.example.studyorder.domain.AbstractEntity;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private List<ItemOption> itemOptions;

    public ItemOptionGroup(Integer ordering, String itemOptionGroupName, Item item,
        List<ItemOption> itemOptions) {
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
        this.item = item;
        this.itemOptions = itemOptions;
    }
}
