package uz.billsplitter.dto.request;

public record DishRequestDto(
        String name,
        double price,
        boolean shared,
        Long personId
) {}