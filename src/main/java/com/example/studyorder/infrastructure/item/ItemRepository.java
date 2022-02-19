package com.example.studyorder.infrastructure.item;

import com.example.studyorder.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item getByItemToken(String itemToken);

}
