package com.example.studyorder.domain.item;

import com.example.studyorder.domain.item.Item.Status;
import com.example.studyorder.domain.item.option.ItemOption;
import com.example.studyorder.domain.item.optiongroup.ItemOptionGroup;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ItemCommand {


    @Getter
    @Builder
    @ToString
    public static class RegisterItemRequest {
        private final String itemName;
        private final Long itemPrice;
        private final Item.Status status;
        private final List<RegisterItemOptionGroupRequest> itemOptionGroupRequests;

        public Item toEntity(Long partnerId){
            return Item.builder()
                .partnerId(partnerId)
                .itemName(itemName)
                .itemPrice(itemPrice)
                .build();
        }

        public boolean isPrepare(){
            return this.status == Status.PREPARE;
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterItemOptionGroupRequest {
        private final Integer ordering;
        private final String itemOptionGroupName;
        private final List<ResisterItemOptionRequest> getItemOptions;

        public ItemOptionGroup toEntity(Item item){
            return ItemOptionGroup.builder()
                .item(item)
                .ordering(ordering)
                .itemOptionGroupName(itemOptionGroupName)
                .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class ResisterItemOptionRequest {
        private final Integer ordering;
        private final String itemOptionName;
        private final Long itemOptionPrice;

        public ItemOption toEntity(ItemOptionGroup itemOptionGroup){
            return ItemOption.builder()
                .ordering(ordering)
                .itemOptionName(itemOptionName)
                .itemOptionPrice(itemOptionPrice)
                .itemOptionGroup(itemOptionGroup)
                .build();
        }

    }
}
