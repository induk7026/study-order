package com.example.studyorder.domain.item;

import com.example.studyorder.domain.item.optiongroup.ItemOptionGroup;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@RequiredArgsConstructor
public class ItemOptionSeriesFactoryImpl implements ItemOptionSeriesFactory{

    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;

    @Override
    public List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item) {
        var itemOptionGroupRequestList = command.getItemOptionGroups();
        if (CollectionUtils.isEmpty(itemOptionGroupRequestList)) return Collections.emptyList();

        return itemOptionGroupRequestList.stream()
            .map(requestItemOptionGroup -> {
                // itemOptionGroup store
                var initItemOptionGroup = requestItemOptionGroup.toEntity(item);
                var itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

                // itemOption store
                requestItemOptionGroup.getGetItemOptions().forEach(requestItemOption -> {
                    var initItemOption = requestItemOption.toEntity(itemOptionGroup);
                    itemOptionStore.store(initItemOption);
                });

                return itemOptionGroup;
            }).collect(Collectors.toList());
    }
}
