package uz.billsplitter.dto.response;

import java.util.List;

public record BillResponseDto(
        double totalBill,
        double serviceFeeValue,
        List<PersonExpenseDto> expenses,
        List<DishResponseDto> dishes
) {}
