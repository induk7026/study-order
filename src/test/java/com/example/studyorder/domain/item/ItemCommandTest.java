package com.example.studyorder.domain.item;

import com.example.studyorder.domain.item.ItemCommand.RegisterItemRequest;
import com.example.studyorder.infrastructure.item.ItemRepository;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ItemCommandTest {

    @Autowired
    private ItemRepository itemRepository;
    @Test
    public void test(){
        List<RegisterItemRequest> list = Lists.newArrayList();
        list.stream()
            .filter(RegisterItemRequest::isPrepare)
            .map( it-> itemRepository.save(it.toEntity(1L)))
            .collect(Collectors.toList());
    }
}