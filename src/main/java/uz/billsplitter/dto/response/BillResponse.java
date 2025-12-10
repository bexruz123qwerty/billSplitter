package uz.billsplitter.dto.response;

import java.util.List;

public record BillResponse(
        Long totalAmount,
        List<PersonalBillResponse> personalBills
) {}
