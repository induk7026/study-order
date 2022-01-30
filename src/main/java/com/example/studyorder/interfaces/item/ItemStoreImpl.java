package com.example.studyorder.interfaces.item;

import com.example.studyorder.domain.item.Item;
import com.example.studyorder.domain.item.ItemStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemStoreImpl implements ItemStore {

    private final ItemRepository itemRepository;

    @Override
    public Item store(Item item) {
        return itemRepository.save(item);
    }
}
