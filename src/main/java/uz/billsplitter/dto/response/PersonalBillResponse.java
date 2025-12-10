package uz.billsplitter.dto.response;

public record PersonalBillResponse(
        String name,
        Long amount
) {}