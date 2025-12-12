package uz.billsplitter.dto.request;

import java.util.List;

public record BillRequestDto(
        double serviceFeePercent,
        List<PersonRequestDto> people,
        List<DishRequestDto> dishes
) {
}

