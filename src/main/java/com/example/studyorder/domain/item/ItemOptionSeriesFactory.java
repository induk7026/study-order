package com.example.studyorder.domain.item;

import com.example.studyorder.domain.item.optiongroup.ItemOptionGroup;
import java.util.List;

public interface ItemOptionSeriesFactory {

    List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item);
}
