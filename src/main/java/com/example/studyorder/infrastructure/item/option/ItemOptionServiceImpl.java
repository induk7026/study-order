package com.example.studyorder.infrastructure.item.option;

import com.example.studyorder.domain.item.option.ItemOptionStore;
import com.example.studyorder.domain.item.option.ItemOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemOptionServiceImpl implements ItemOptionStore {

    private final ItemOptionRepository itemOptionRepository;

    @Override
    public ItemOption store(ItemOption initItemOption) {
        return itemOptionRepository.save(initItemOption);
    }
}
