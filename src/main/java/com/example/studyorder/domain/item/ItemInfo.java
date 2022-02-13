package com.example.studyorder.domain.item;

import com.example.studyorder.domain.item.Item.Status;
import com.example.studyorder.domain.item.option.ItemOption;
import com.example.studyorder.domain.item.optiongroup.ItemOptionGroup;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ItemInfo {

    @Getter
    @ToString
    public static class Main {
        private final String itemToken;
        private final Long partnerId;
        private final String itemName;
        private final Long itemPrice;
        private final Item.Status status;
        private final List<ItemOptionGroup> itemOptionGroups;

        @Builder
        public Main(Item item, List<ItemOptionGroup> itemOptionGroups) {
            this.itemToken = item.getItemToken();
            this.partnerId = item.getPartnerId();
            this.itemName = item.getItemName();
            this.itemPrice = item.getItemPrice();
            this.status = item.getStatus();
            this.itemOptionGroups = itemOptionGroups;
        }
    }

    @Getter
    @ToString
    public static class ItemOptionGroupInfo {
        private final Integer ordering;
        private final String itemOptionGroupName;
        private final List<ItemOption> itemOptions;

        @Builder
        public ItemOptionGroupInfo(ItemOptionGroup itemOptionGroup, List<ItemOption> itemOptions) {
            this.ordering = itemOptionGroup.getOrdering();
            this.itemOptionGroupName = itemOptionGroup.getItemOptionGroupName();
            this.itemOptions = itemOptions;
        }
    }

    @Getter
    @ToString
    public static class ItemOptionInfo {
        private final Integer ordering;
        private final String itemOptionName;
        private final Long itemOptionPrice;

        @Builder
        public ItemOptionInfo(ItemOption itemOption) {
            this.ordering = itemOption.getOrdering();
            this.itemOptionName = itemOption.getItemOptionName();
            this.itemOptionPrice = itemOption.getItemOptionPrice();
        }
    }
}
