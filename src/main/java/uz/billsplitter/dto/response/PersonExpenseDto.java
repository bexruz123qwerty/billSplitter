package uz.billsplitter.dto.response;

public record PersonExpenseDto(
        Long personId,
        String name,
        double total
) {}
