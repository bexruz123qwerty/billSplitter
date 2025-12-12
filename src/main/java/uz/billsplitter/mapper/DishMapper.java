package uz.billsplitter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.billsplitter.dto.request.DishRequestDto;
import uz.billsplitter.dto.response.DishResponseDto;
import uz.billsplitter.entity.Dish;
import uz.billsplitter.entity.Person;

import java.util.List;

@Mapper()
public interface DishMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", source = "person")
    Dish toEntity(DishRequestDto dto, Person person);

    @Mapping(target = "personId", source = "person.id")
    DishResponseDto toResponseDto(Dish dish);

    List<DishResponseDto> toResponseList(List<Dish> dishes);
}
