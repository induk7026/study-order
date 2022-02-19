package com.example.studyorder.interfaces.item;

import com.example.studyorder.domain.item.ItemCommand;
import com.example.studyorder.domain.item.ItemInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemDtoMapper {

    @Mappings({
        @Mapping(source = "request.itemOptionGroups", target = "itemOptionGroupRequests")
    })
    ItemCommand.RegisterItemRequest of(ItemDto.RegisterItemRequest request);

    @Mappings({@Mapping(source = "itemOptions", target = "itemOptionRequests")})
    ItemCommand.RegisterItemOptionGroupRequest of(ItemDto.RegisterItemOptionGroupRequest request);

    ItemCommand.RegisterItemOptionRequest of(ItemDto.RegisterItemOptionRequest request);

    ItemDto.RegisterResponse of(String itemToken);

    // retrieve
    ItemDto.Main of(ItemInfo.Main main);

    ItemDto.ItemOptionGroupInfo of(ItemInfo.ItemOptionGroupInfo itemOptionGroup);

    ItemDto.ItemOptionInfo of(ItemInfo.ItemOptionInfo itemOption);
}
