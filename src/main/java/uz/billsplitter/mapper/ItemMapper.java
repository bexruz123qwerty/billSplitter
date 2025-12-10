package uz.billsplitter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.billsplitter.dto.request.ItemRequest;
import uz.billsplitter.entity.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "person", source = "personName")
    Item toEntity(ItemRequest request);
}
