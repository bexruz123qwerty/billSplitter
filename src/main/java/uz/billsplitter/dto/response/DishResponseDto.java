package uz.billsplitter.dto.response;

public record DishResponseDto(
        Long id,
        String name,
        double price,
        boolean shared,
        Long personId
) {}
