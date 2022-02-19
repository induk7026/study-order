package com.example.studyorder.domain.item;

import com.example.studyorder.domain.item.ItemInfo.ItemOptionGroupInfo;
import com.example.studyorder.infrastructure.item.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ItemReaderImpl implements ItemReader {

    private final ItemRepository itemRepository;

    @Override
    public Item getItemToken(String itemToken) {
        return itemRepository.getByItemToken(itemToken);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemOptionGroupInfo> getItemOptionSeries(Item item) {
        var itemOptionGroups = item.getItemOptionGroups();
        return itemOptionGroups.stream()
            .map(itemOptionGroup -> {
                var itemOptions = itemOptionGroup.getItemOptions();
                var itemOptionInfos = itemOptions.stream()
                    .map(ItemInfo.ItemOptionInfo::new)
                    .collect(Collectors.toList());
                return new ItemInfo.ItemOptionGroupInfo(itemOptionGroup, itemOptionInfos);
            }).collect(Collectors.toList());
    }
}
