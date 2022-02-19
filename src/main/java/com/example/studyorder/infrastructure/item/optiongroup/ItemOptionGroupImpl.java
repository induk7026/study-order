package com.example.studyorder.infrastructure.item.optiongroup;

import com.example.studyorder.domain.item.optiongroup.ItemOptionGroupStore;
import com.example.studyorder.domain.item.optiongroup.ItemOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemOptionGroupImpl implements ItemOptionGroupStore {

    private final ItemOptionGroupRepository itemOptionGroupRepository;

    @Override
    public ItemOptionGroup store(ItemOptionGroup initItemOptionGroup) {
        return itemOptionGroupRepository.save(initItemOptionGroup);
    }
}
