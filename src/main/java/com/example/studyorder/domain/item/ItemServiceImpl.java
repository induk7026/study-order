package com.example.studyorder.domain.item;

import com.example.studyorder.domain.item.ItemInfo.Main;
import com.example.studyorder.domain.partner.PartnerReader;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PartnerReader partnerReader;
    private final ItemStore itemStore;
    private final ItemReader itemReader;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;

    @Override
    public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken) {
        var partner = partnerReader.getPartner(partnerToken);
        var initItem = command.toEntity(partner.getId());
        var item = itemStore.store(initItem);
        itemOptionSeriesFactory.store(command, item);
        return item.getItemToken();
    }

    @Transactional
    @Override
    public void changeOnSale(String itemToken){
        var item = itemReader.getItemToken(itemToken);
        item.changeOnSales();
    }

    @Transactional
    @Override
    public void changePrePare(String itemToken) {
        var item = itemReader.getItemToken(itemToken);
        item.changePrePare();
    }

    @Transactional
    @Override
    public void changeEndOfSale(String itemToken) {
        var item = itemReader.getItemToken(itemToken);
        item.changeEndOfSales();
    }

    @Override
    public Main retrieveItemInfo(String itemToken) {
        var item = itemReader.getItemToken(itemToken);
        var itemOptionGroupInfoList = itemReader.getItemOptionSeries(item);
        return new ItemInfo.Main(item, itemOptionGroupInfoList);
    }
}
