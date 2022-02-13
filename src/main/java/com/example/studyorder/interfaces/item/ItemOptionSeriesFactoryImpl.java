package com.example.studyorder.interfaces.item;

import com.example.studyorder.domain.item.Item;
import com.example.studyorder.domain.item.ItemCommand.RegisterItemRequest;
import com.example.studyorder.domain.item.ItemOptionSeriesFactory;
import com.example.studyorder.domain.item.option.ItemOptionStore;
import com.example.studyorder.domain.item.optiongroup.ItemOptionGroup;
import com.example.studyorder.domain.item.optiongroup.ItemOptionGroupStore;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@RequiredArgsConstructor
public class ItemOptionSeriesFactoryImpl implements ItemOptionSeriesFactory {

    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;

    @Override
    public List<ItemOptionGroup> store(RegisterItemRequest command, Item item) {
        var itemOptionGroupRequests = command.getItemOptionGroupRequests();
        if (CollectionUtils.isEmpty(itemOptionGroupRequests)) return Collections.emptyList();

        return itemOptionGroupRequests.stream()
            .map(requestItemOptionGroup -> {
                // itemOptionGroup store
                var initItemOptionGroup = requestItemOptionGroup.toEntity(item);
                var itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

                // itemOption store
                requestItemOptionGroup.getItemOptions().forEach(requestItemOption -> {
                    var initItemOption = requestItemOption.toEntity(itemOptionGroup);
                    itemOptionStore.store(initItemOption);
                });

                return itemOptionGroup;
            }).collect(Collectors.toList());
    }
}
