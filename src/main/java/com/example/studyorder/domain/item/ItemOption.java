package com.example.studyorder.domain.item;

import com.example.studyorder.domain.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "itemOptionGroupId")
    private ItemOptionGroup itemOptionGroup;

    public ItemOption(Integer ordering, String itemOptionName, Long itemOptionPrice,
        ItemOptionGroup itemOptionGroup) {
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
        this.itemOptionGroup = itemOptionGroup;
    }
}
