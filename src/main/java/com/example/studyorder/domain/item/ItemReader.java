package com.example.studyorder.domain.item;

import com.example.studyorder.domain.item.ItemInfo.ItemOptionGroupInfo;
import java.util.List;

public interface ItemReader {

    Item getItemToken(String itemToken);

    List<ItemOptionGroupInfo> getItemOptionSeries(Item item);
}
