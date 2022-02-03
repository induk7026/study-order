package com.example.studyorder.domain.item;

import com.example.studyorder.interfaces.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemReaderImpl implements ItemReader {

    private final ItemRepository itemRepository;

    @Override
    public Item getItemToken(String itemToken) {
        return itemRepository.getByItemToken(itemToken);
    }
}
