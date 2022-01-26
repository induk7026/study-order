package com.example.studyorder.domain.item;

public interface ItemService {

    public String registerItem(ItemCommand.RegisterItemRequest registerItemRequest, String partnerToken);
}
