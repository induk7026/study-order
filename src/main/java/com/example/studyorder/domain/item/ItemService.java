package com.example.studyorder.domain.item;

public interface ItemService {

    String registerItem(ItemCommand.RegisterItemRequest registerItemRequest, String partnerToken);

    void changeOnSale(String itemToken);
    void changePrePare(String itemToken);
    void changeEndOfSales(String itemToken);
}
