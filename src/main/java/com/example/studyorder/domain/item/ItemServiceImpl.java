package com.example.studyorder.domain.item;

import com.example.studyorder.domain.item.option.ItemOption;
import com.example.studyorder.domain.item.optiongroup.ItemOptionGroup;
import com.example.studyorder.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PartnerReader partnerReader;
    private final ItemReader itemReader;
    private final ItemStore itemStore;
    private final ItemOptionStore itemOptionStore;
    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;

    @Override
    public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken) {
        var partner = partnerReader.getPartner(partnerToken);
        var initItem = command.toEntity(partner.getId());
        var item = itemStore.store(initItem);
        itemOptionSeriesFactory.store(command, item);
        return item.getItemToken();
    }
}
