package uz.billsplitter.dto.response;

import java.util.List;

public record BillResponseDto(
        Long totalAmount,
        List<PersonalBillResponse> personalBills
) {}
