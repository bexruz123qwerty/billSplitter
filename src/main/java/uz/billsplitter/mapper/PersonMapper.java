package uz.billsplitter.mapper;

import org.mapstruct.Mapper;
import uz.billsplitter.dto.request.PersonRequestDto;
import uz.billsplitter.entity.Person;

import java.util.List;

@Mapper()
public interface PersonMapper {

    Person toEntity(PersonRequestDto dto);

    PersonRequestDto toRequestDto(Person person);

    List<Person> toEntityList(List<PersonRequestDto> dtoList);
}
