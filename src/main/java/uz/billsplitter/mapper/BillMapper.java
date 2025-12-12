package uz.billsplitter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.billsplitter.dto.request.BillRequestDto;
import uz.billsplitter.entity.Bill;

@Mapper
public interface BillMapper {

    @Mapping(target = "id", ignore = true)
    Bill toEntity(BillRequestDto dto);
}
